package com.equilibrium.kiriman.services;

import com.equilibrium.kiriman.entities.dto.CekStatus;

/**
 * Created by faisalw on 3/18/17.
 */
public interface CekResponService {

    // Cek respon yang sudah terkirim sampai mana
    //void cekAndUpdateResponSentDocuments();

    // Cek dan kirim ulang dokumen yg belum berhasil terkirim karena Timeout
    void cekDanKirimUlangDokBelumTerkirim();

    void cekSemuaResponTerbaru();

}
