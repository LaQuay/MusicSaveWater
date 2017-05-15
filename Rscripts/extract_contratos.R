library(data.table)
consumosDS = fread("5. Consumos_detallado_slice 2.txt")
dim(consumosDS)

contratos = subset(consumosDS, !duplicated(ContratoCOD))
dim(contratos)

contratos$CONSUM <- NULL
contratos$ANYMES_CAL <- NULL
dim(contratos)

contratos = contratos[contratos$USO == "DOMESTIC"]
dim(contratos)

contratos = contratos[contratos$DESC_TIP_SUBM %in% c("SUBMINISTRAMENT COMPTADOR DIVISIONARI", "SUBMINISTRAMENT COMPTADOR GENERAL")]
dim(contratos)

write.table(contratos,"contratos2.txt",sep=";", row.names = FALSE)

rm(list=ls())
consumosDS = fread("5. Consumos_detallado_slice 1.txt")
dim(consumosDS)

contratos = subset(consumosDS, !duplicated(ContratoCOD))
dim(contratos)

contratos$CONSUM <- NULL
contratos$ANYMES_CAL <- NULL
dim(contratos)

contratos = contratos[contratos$USO == "DOMESTIC"]
dim(contratos)

contratos = contratos[contratos$DESC_TIP_SUBM %in% c("SUBMINISTRAMENT COMPTADOR DIVISIONARI", "SUBMINISTRAMENT COMPTADOR GENERAL")]
dim(contratos)

write.table(contratos,"contratos1.txt",sep=";", row.names = FALSE)
