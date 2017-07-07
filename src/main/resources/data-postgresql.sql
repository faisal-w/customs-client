INSERT INTO ask_treck."user" (id_user, nama, username, password, role, enabled)
SELECT 3,'Super Admin','superadmin','$2a$10$EdhCvAzptfaQgjDmgR4xXu/m3VmxWstL8nhWQqUH1eHpr6u4aJThW','ROLE_SUPERADMIN',true
WHERE NOT EXISTS (SELECT 1 FROM ask_treck."user" WHERE id_user=3);

INSERT INTO ask_treck.perusahaan (id_perusahaan, nama_perusahaan, npwp, alamat, userwsbea_cukai, passwsbea_cukai, tokenwsbea_cukai, is_aktif)
SELECT 2,'Perusahaan Coba-coba','123456789012345','Jalan Wara-wiri 1','userdemo','123456','TVRJek5EVTJOemc1TFRFMw0K',true
WHERE NOT EXISTS (SELECT 1 FROM ask_treck.perusahaan WHERE id_perusahaan=2);

INSERT INTO ask_treck."user" (id_user,nama,username,password,role,enabled,client_id_perusahaan)
SELECT 4,'User Biasa','user','$2a$10$Ewvp/EM6ttXKG8dw8dsEaePsXDK343ju0L/HVpv3b0IIhujXFr.L6','ROLE_USER',true,2
WHERE NOT EXISTS (SELECT 1 FROM ask_treck."user" WHERE id_user=4);

INSERT INTO ask_treck.perusahaan_pegawais (perusahaan_id_perusahaan,pegawais_id_user)
SELECT 2,4
WHERE NOT EXISTS (SELECT 1 FROM ask_treck.perusahaan_pegawais WHERE perusahaan_id_perusahaan=2 AND pegawais_id_user=4);

--ALTER USER postgres SET search_path to 'ask_treck';
