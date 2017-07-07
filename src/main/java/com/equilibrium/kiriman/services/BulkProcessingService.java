package com.equilibrium.kiriman.services;

import java.io.File;

/**
 * Created by faisalw on 4/26/17.
 */
public interface BulkProcessingService {

    String[] bacaDanValidasiAwalFileUpload(File namaFile, String npwp);

    void validasiLanjut(int idPekerjaan);

    void simpanDokumens(File namaFile,int idPekerjaan);

    void kirimDokumen(int idPekerjaan);

}
