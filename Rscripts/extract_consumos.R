library(data.table)
consumos1 = fread("5. Consumos_detallado_slice 1.txt")
dim(consumos1)

consumos1 = consumos1[consumos1$USO == "DOMESTIC"]
dim(consumos1)

consumos1 = consumos1[consumos1$DESC_TIP_SUBM %in% c("SUBMINISTRAMENT COMPTADOR DIVISIONARI", "SUBMINISTRAMENT COMPTADOR GENERAL")]
dim(consumos1)

consumos1$DESC_MUNI = NULL
consumos1$DES_DISTRIC = NULL
consumos1$DESC_TIP_SUBM = NULL
consumos1$USO = NULL
dim(consumos1)

write.table(consumos1,"consumos1.txt",sep=";", row.names = FALSE)

rm(list=ls())
consumos2 = fread("5. Consumos_detallado_slice 2.txt")

consumos2 = consumos2[consumos2$USO == "DOMESTIC"]

consumos2 = consumos2[consumos2$DESC_TIP_SUBM %in% c("SUBMINISTRAMENT COMPTADOR DIVISIONARI", "SUBMINISTRAMENT COMPTADOR GENERAL")]

consumos2$DESC_MUNI = NULL
consumos2$DES_DISTRIC = NULL
consumos2$DESC_TIP_SUBM = NULL
consumos2$USO = NULL

write.table(consumos2,"consumos2.txt",sep=";", row.names = FALSE)
