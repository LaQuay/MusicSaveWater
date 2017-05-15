library(data.table)
consumos1 = fread("consumos1.txt")
consumos2 = fread("consumos2.txt")

dim(consumos1)
dim(consumos2)

consumos = rbind(consumos1, consumos2)
dim(consumos)

write.table(consumos,"consumos.csv",sep=",", row.names = FALSE)
