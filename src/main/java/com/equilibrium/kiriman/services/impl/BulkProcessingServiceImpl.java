package com.equilibrium.kiriman.services.impl;

import com.equilibrium.kiriman.entities.*;
import com.equilibrium.kiriman.services.BulkProcessingService;
import com.equilibrium.kiriman.services.KirimDataService;
import com.equilibrium.kiriman.services.repository.LogCnPibkRepo;
import com.equilibrium.kiriman.services.repository.LogExcelRepo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by faisalw on 4/26/17.
 */
@Service("bulkProcessingService")
public class BulkProcessingServiceImpl implements BulkProcessingService {

    @Autowired
    LogCnPibkRepo cnPibkRepo;
    @Autowired
    LogExcelRepo logExcelRepo;
    @Autowired
    KirimDataService kirimDataService;

    String[] namaKolomHdr = {"NO","KODE KANTOR","JENIS DOKUMEN","JENIS PIBK","NO BARANG","JENIS ANGKUT",
            "NAMA ANGKUT","NO.VOY","PEL.MUAT","PEL.BONGKAR","KODE GUDANG","NO INVOICE",
            "JUMLAH BARANG","NEGARA ASAL","NOBC11","TGLBC11","POS","SUBPOS","SUBSUBPOS",
            "NOMAWB","TGLMAWB","NOHAWB","TGLHAWB","NEGARA SHIPPER","NAMA SHIPPER","ALAMAT SHIPPER",
            "JENIS ID PENERIMA","NO ID PENERIMA","NAMA PENERIMA","ALAMAT PENERIMA","TELP PENERIMA",
            "JENIS ID PEMBERITAHU","NO ID PEMBERITAHU","NAMA PEMBERITAHU","ALAMAT PEMBERITAHU",
            "NO IJIN","TGL IJIN","VALUTA","NDPBM","FOB","ASURANSI","FREIGHT","CIF","NETTO",
            "BRUTTO","TOT.BAYAR","NPWP BILLING","NAMA BILLING","BM","PPH","PPN","PPNBM"};
    String[] namaKolomDtl = {"NO","NO BARANG","NOHAWB","SERI BARANG","HS CODE","URAIAN BARANG","NEGARA ASAL",
            "JUMLAH KEMASAN","JENIS KEMASAN","CIF","JENIS SATUAN","HARGA SATUAN","PEMBEBASAN","NO SKEP",
            "TGL SKEP","KODE TARIF","BM PER","PPH PER","PPN PER","PPNBM PER","BM BAYAR","PPH BAYAR",
            "PPN BAYAR","PPNBM BAYAR"};

    @Override
    public String[] bacaDanValidasiAwalFileUpload(File namaFile,String npwp) {
        LogPekerjaanExcel logExcel = new LogPekerjaanExcel();
        String[] res = {"",""};
        String errorList="";
        try{
            FileInputStream tempFile = new FileInputStream(namaFile);
            Workbook workbook = new HSSFWorkbook(tempFile);
            Sheet headerSheet = workbook.getSheetAt(0);
            if(null==headerSheet){
                errorList = "FILE EXCEL KOSONG/TIDAK BISA DIBACA";
                res[0] = errorList;
                return res;
            }
            int cellNum=0,rowNum = 0;
            Iterator<Row> hdrIter = headerSheet.iterator();
            while (hdrIter.hasNext()){
                Row currentRow = hdrIter.next();
                Iterator<Cell> cellIter = currentRow.iterator();
                if(rowNum==0) {
                    while (cellIter.hasNext()) {
                        Cell cell = cellIter.next();
                        //cell.getNumericCellValue();
                        //cell.getStringCellValue().equals("NO");
                        cellNum++;
                    }
                    if(cellNum!=namaKolomHdr.length){
                        errorList += "\nJumlah kolom Header Dokumen tidak sesuai dengan contoh.";
                    }
                }else{
                    if(!cellIter.hasNext()){
                        errorList += "\nIsian Header Dokumen kosong.";
                        break;
                    }
                }
                rowNum++;
            }

            Sheet detilBrgSheet = workbook.getSheetAt(1);
            if(null==detilBrgSheet)
                errorList +=  "\nDETAIL BARANG KOSONG";
            cellNum=0;rowNum=0;
            Iterator<Row> dtlIter = detilBrgSheet.iterator();
            /*while(dtlIter.hasNext()){
            }*/
            logExcel.setNpwp(npwp);
            logExcel.setChecked(false);
            logExcel.setSent(false);
        }catch (Exception e){
            e.printStackTrace();
            errorList += "\nFile excel tidak bisa dibaca, coba buat ulang.";
        }
        if(errorList.length()==0){
            errorList = "Upload sukses, dalam proses validasi detail.";
            logExcelRepo.save(logExcel);
            res[1] = logExcel.getId()+"";
        }else {
            errorList += "\nMohon periksa lagi isian excel anda.";
            //namaFile.delete();
        }
        System.out.println(errorList);
        res[0] = errorList;
        return res;
    }

    @Override
    public void validasiLanjut(int idPekerjaan) {

    }

    @Override
    @Transactional
    public void simpanDokumens(File namaFile,int idPekerjaan) {
        LogPekerjaanExcel logExcel = logExcelRepo.findOne(idPekerjaan);
        try{
            FileInputStream tempFile = new FileInputStream(namaFile);
            Workbook workbook = new HSSFWorkbook(tempFile);
            Sheet headerSheet = workbook.getSheetAt(0);
            Sheet detilBrgSheet = workbook.getSheetAt(1);
            int cellNum=0,rowNum = 0,totalBrg=0;
            Iterator<Row> hdrIter = headerSheet.iterator();
            //Iterator<Row> detilIter = detilBrgSheet.iterator();
            List<LogCnPibk> komplotanCnPibk = new ArrayList<LogCnPibk>();
            while (hdrIter.hasNext()){
                Row currRow = hdrIter.next();
                if(rowNum==0){
                    System.out.println("Heder header");
                    rowNum++;
                    //currRow = hdrIter.next();
                    continue;
                }
                System.out.println("current row : "+currRow.getCell(1).getStringCellValue());
                if(currRow.getCell(1).getStringCellValue().isEmpty()){
                    break;
                }
                LogCnPibk tempCn = new LogCnPibk();
                tempCn = this.assignRowToHeader(tempCn,currRow);
                //Use filter??queries?none can do
                    //get column where no_barang reside
                    //get no_barang same with Header_noBarang
                int detilNum=0;
                List<LogDetilBarang> detilBarangs = new ArrayList<LogDetilBarang>();
                Iterator<Row> detilIter = detilBrgSheet.iterator();
                while(detilIter.hasNext()){
                    Row dtlRow = detilIter.next();
                    if(dtlRow.getCell(1).getStringCellValue().trim().equals(tempCn.getNoBarang().trim())){
                        //proses
                        LogDetilBarang tempDtl = new LogDetilBarang();
                        tempDtl = this.assignRowToDetilBrg(tempDtl,dtlRow);
                        tempDtl.setCnPibk(tempCn);
                        detilBarangs.add(tempDtl);
                        //dtlRow.getRowNum();
                        //detilBrgSheet.getLastRowNum();
                       // detilBrgSheet.removeRow();
                        //detilBrgSheet.removeRow(dtlRow); //CHECK THIS!!!
                        detilNum++;
                    }else{
                        continue;
                    }
                    if(detilNum==tempCn.getJmlBrg())
                        break;
                }
                detilIter = null;
                this.removeEmptyRows(detilBrgSheet);
                tempCn.setDetilBarangs(detilBarangs);
                totalBrg += tempCn.getDetilBarangs().size();
                System.out.println("Barang nomor : "+rowNum+", "+tempCn.toString());
                System.out.println("Isi detail : "+tempCn.getDetilBarangs().size()+" barang");
                komplotanCnPibk.add(tempCn);
            }
            logExcel.setLogCnPibks(komplotanCnPibk);
            //cnPibkRepo.save(komplotanCnPibk);
            logExcel.setTotalHeader(komplotanCnPibk.size());
            logExcel.setTotalBarang(totalBrg);
            logExcel.setKeterangan("Sukses upload,menunggu validasi isian.");
        }catch(Exception e){
            e.printStackTrace();
            //notify client for errors!
            logExcel.setKeterangan(e.getMessage());
        }
        logExcelRepo.save(logExcel);
    }

    @Override
    public void kirimDokumen(int idPekerjaan) {
        LogPekerjaanExcel logExcel = logExcelRepo.findOne(idPekerjaan);
        List<LogCnPibk> cnPibks = logExcel.getLogCnPibks();
        for (LogCnPibk cn:cnPibks) {
            kirimDataService.kirimDataSatuan(cn);
        }
    }

    LogCnPibk assignRowToHeader(LogCnPibk tempCn,Row row) throws Exception{
        DataFormatter dtf = new DataFormatter();

        Cell tempCell = row.getCell(1);
        tempCn.setKdKantor(dtf.formatCellValue(tempCell));
        tempCn.setJnsAju(row.getCell(2).getNumericCellValue()+"");
        tempCn.setKdJnsPibk(new Double(row.getCell(3).getNumericCellValue()).intValue()+"");
        tempCn.setNoBarang(row.getCell(4).getStringCellValue()+"");
        tempCn.setKdJnsAngkut(new Double(row.getCell(5).getNumericCellValue()).intValue());
        tempCn.setNmPengangkut(row.getCell(6).getStringCellValue()+"");
        tempCn.setNoFlight(row.getCell(7).getStringCellValue()+"");
        tempCn.setKdPelMuat(row.getCell(8).getStringCellValue()+"");
        tempCn.setKdPelBongkar(row.getCell(9).getStringCellValue()+"");
        tempCn.setKdGudang(row.getCell(10).getStringCellValue()+"");

        tempCell = row.getCell(11);
        tempCn.setNoInvoice(dtf.formatCellValue(tempCell));
        tempCn.setJmlBrg(new Double(row.getCell(12).getNumericCellValue()).intValue());
        tempCn.setKdNegaraAsal(row.getCell(13).getStringCellValue()+"");

        tempCell = row.getCell(14);
        tempCn.setNoBc11(dtf.formatCellValue(tempCell));
        tempCn.setTglBc11(row.getCell(15).getDateCellValue());

        tempCell = row.getCell(16);
        tempCn.setNoPosBc11(dtf.formatCellValue(tempCell));
        tempCell = row.getCell(17);
        tempCn.setNoSubposBc11(dtf.formatCellValue(tempCell));
        tempCell = row.getCell(18);
        tempCn.setNoSubsubposBc11(dtf.formatCellValue(tempCell));
        tempCell = row.getCell(19);
        tempCn.setNoMasterBlawb(dtf.formatCellValue(tempCell));
        tempCn.setTglMasterBlawb(row.getCell(20).getDateCellValue());
        tempCell = row.getCell(21);
        tempCn.setNoHouseBlawb(dtf.formatCellValue(tempCell));
        tempCn.setTglHouseBlawb(row.getCell(22).getDateCellValue());
        tempCn.setKdNegPengirim(row.getCell(23).getStringCellValue()+"");
        tempCn.setNmPengirim(row.getCell(24).getStringCellValue()+"");
        tempCn.setAlPengirim(row.getCell(25).getStringCellValue()+"");
        tempCell = row.getCell(26);
        tempCn.setJnsIdPenerima(dtf.formatCellValue(tempCell));
        tempCell = row.getCell(27);
        tempCn.setNoIdPenerima(dtf.formatCellValue(tempCell));
        tempCn.setNmPenerima(row.getCell(28).getStringCellValue()+"");
        tempCn.setAlPenerima(row.getCell(29).getStringCellValue()+"");
        tempCn.setTelpPenerima(row.getCell(30).getStringCellValue()+"");
        tempCell = row.getCell(31);
        tempCn.setJnsIdPemberitahu(dtf.formatCellValue(tempCell));
        tempCell = row.getCell(32);
        tempCn.setNoIdPemberitahu(dtf.formatCellValue(tempCell));
        tempCn.setNmPemberitahu(row.getCell(33).getStringCellValue()+"");
        tempCn.setAlPemberitahu(row.getCell(34).getStringCellValue()+"");
        tempCell = row.getCell(35);
        tempCn.setNoIzinPemberitahu(dtf.formatCellValue(tempCell));
        tempCn.setTglIzinPemberitahu(row.getCell(36).getDateCellValue());
        tempCn.setKdVal(row.getCell(37).getStringCellValue()+"");
        tempCn.setNdpbm(new BigDecimal(row.getCell(38).getNumericCellValue()));
        tempCn.setFob(new BigDecimal(row.getCell(39).getNumericCellValue()));
        tempCn.setAsuransi(new BigDecimal(row.getCell(40).getNumericCellValue()));
        tempCn.setFreight(new BigDecimal(row.getCell(41).getNumericCellValue()));
        tempCn.setCif(new BigDecimal(row.getCell(42).getNumericCellValue()));
        tempCn.setNetto(new BigDecimal(row.getCell(43).getNumericCellValue()));
        tempCn.setBruto(new BigDecimal(row.getCell(44).getNumericCellValue()));
        tempCn.setTotDibayar(new BigDecimal(row.getCell(45).getNumericCellValue()));
        tempCn.setNpwpBilling(row.getCell(46).getStringCellValue()+"");
        tempCn.setNamaBilling(row.getCell(47).getStringCellValue()+"");
        //--HEADER PUNGUTAN--//
        List<LogHeaderPungutan> headerPungutans = new ArrayList<LogHeaderPungutan>();
        if(null!=row.getCell(48)) {
            LogHeaderPungutan headerBm = new LogHeaderPungutan(1, new BigDecimal(row.getCell(48).getNumericCellValue()));
            headerPungutans.add(headerBm);
        }
        if(null!=row.getCell(49)) {
            LogHeaderPungutan headerPph = new LogHeaderPungutan(2, new BigDecimal(row.getCell(49).getNumericCellValue()));
            headerPungutans.add(headerPph);
        }
        if(null!=row.getCell(50)) {
            LogHeaderPungutan headerPpn = new LogHeaderPungutan(3, new BigDecimal(row.getCell(50).getNumericCellValue()));
            headerPungutans.add(headerPpn);
        }
        if(null!=row.getCell(51)) {
            LogHeaderPungutan headerPpnbm = new LogHeaderPungutan(4, new BigDecimal(row.getCell(51).getNumericCellValue()));
            headerPungutans.add(headerPpnbm);
        }
        tempCn.setHeaderPungutans(headerPungutans);
        //--END--//
        return tempCn;
    }

    LogDetilBarang assignRowToDetilBrg(LogDetilBarang tempDtl,Row row) throws Exception{
        DataFormatter dtf = new DataFormatter();
        tempDtl.setSeriBrg(new Double(row.getCell(3).getNumericCellValue()).intValue());
        Cell tempCell = row.getCell(4);
        tempDtl.setHsCode(dtf.formatCellValue(tempCell));
        tempDtl.setUrBrg(row.getCell(5).getStringCellValue());
        tempDtl.setKdNegAsal(row.getCell(6).getStringCellValue());
        tempDtl.setJmlKms(new Double(row.getCell(7).getNumericCellValue()).toString());
        tempDtl.setJnsKms(row.getCell(8).getStringCellValue());
        tempDtl.setCif(new BigDecimal(row.getCell(9).getNumericCellValue()));
        tempDtl.setKdSatHrg(row.getCell(10).getStringCellValue());
        tempCell = row.getCell(11);
        if(dtf.formatCellValue(tempCell).isEmpty())
            if(!dtf.formatCellValue(tempCell).equals("-"))
                tempDtl.setJmlSatHrg(new BigDecimal(new Double(dtf.formatCellValue(tempCell))));
        tempCell = row.getCell(12);
        tempDtl.setFlBebas(dtf.formatCellValue(tempCell));
        tempDtl.setNoSkep(row.getCell(13).getStringCellValue());
        tempCell = row.getCell(14);
        if(dtf.formatCellValue(tempCell).isEmpty())
            if(!dtf.formatCellValue(tempCell).equals("-"))
                tempDtl.setTglSkep(tempCell.getDateCellValue());
        //--Detil PUNGUTAN--//
        List<LogDetilPungutan> detilPungutans = new ArrayList<LogDetilPungutan>();
        if(null!=row.getCell(15)) {
            LogDetilPungutan pungutanBM = new LogDetilPungutan();
            pungutanBM.setKdTarif("1");
            pungutanBM.setTarif(row.getCell(15).getNumericCellValue());
            if(pungutanBM.getTarif()>0)
                pungutanBM.setNilai(new BigDecimal(row.getCell(19).getNumericCellValue()));
            pungutanBM.setBarang(tempDtl);
            detilPungutans.add(pungutanBM);
        }
        if(null!=row.getCell(16)) {
            LogDetilPungutan pungutanPph = new LogDetilPungutan();
            pungutanPph.setKdTarif("2");
            pungutanPph.setTarif(row.getCell(16).getNumericCellValue());
            if(pungutanPph.getTarif()>0)
                pungutanPph.setNilai(new BigDecimal(row.getCell(20).getNumericCellValue()));
            pungutanPph.setBarang(tempDtl);
            detilPungutans.add(pungutanPph);
        }
        if(null!=row.getCell(17)) {
            LogDetilPungutan pungutanPpn = new LogDetilPungutan();
            pungutanPpn.setKdTarif("3");
            pungutanPpn.setTarif(row.getCell(17).getNumericCellValue());
            if(pungutanPpn.getTarif()>0)
                pungutanPpn.setNilai(new BigDecimal(row.getCell(21).getNumericCellValue()));
            pungutanPpn.setBarang(tempDtl);
            detilPungutans.add(pungutanPpn);
        }
        if(null!=row.getCell(18)) {
            LogDetilPungutan pungutanPpnBm = new LogDetilPungutan();
            pungutanPpnBm.setKdTarif("4");
            pungutanPpnBm.setTarif(row.getCell(18).getNumericCellValue());
            if(pungutanPpnBm.getTarif()>0)
                pungutanPpnBm.setNilai(new BigDecimal(row.getCell(22).getNumericCellValue()));
            pungutanPpnBm.setBarang(tempDtl);
            detilPungutans.add(pungutanPpnBm);
        }
        //--END--//
        tempDtl.setDetilPungutans(detilPungutans);
        return tempDtl;
    }

    /**
     * Removes rows where the column B is empty.
     * @param sheet the sheet where rows should be removed
     */
    private void removeEmptyRows(final Sheet sheet) {
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            boolean isRowEmpty;
            if (sheet.getRow(i)==null) {
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                i--;
                continue;
            }
            final Row actualRow = sheet.getRow(i);
            isRowEmpty =
                    actualRow.getCell(1).toString().trim().equals("");
            if (isRowEmpty) {
                if (i == sheet.getLastRowNum()) {
                    sheet.removeRow(actualRow);
                } else {
                    sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                }
                i--;
            }
        }
    }

}
