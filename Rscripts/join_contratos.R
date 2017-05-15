library(data.table)
contratos1 = fread("contratos1.txt")
contratos2 = fread("contratos2.txt")

dim(contratos1)
dim(contratos2)

contratos = rbind(contratos1, contratos2)
dim(contratos)

length(unique(contratos$ContratoCOD))
contratos = subset(contratos, !duplicated(ContratoCOD))
dim(contratos)

write.table(contratos,"contratos.csv",sep=",", row.names = FALSE)
