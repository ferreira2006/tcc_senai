DROP DATABASE tcc;

CREATE DATABASE IF NOT EXISTS tcc;

USE tcc;

CREATE TABLE IF NOT EXISTS senha (
  chave int(7) NOT NULL auto_increment PRIMARY KEY,
  nome varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  usuario varchar(30) NOT NULL,
  pass varchar(32) NOT NULL,
  dica varchar(30), 
  supervisor char(1)
);

INSERT INTO senha (chave, nome, email, usuario, pass, dica, supervisor) VALUES (null, 'administrador', 'ferreira2006@gmail.com', 'adm', 'b09c600fddc573f117449b3723f23d64', 'sem dica', '1');


CREATE TABLE IF NOT EXISTS arvore (
chave int(7) NOT NULL auto_increment primary key,
nomePopular VARCHAR(75),
nomeCientifico VARCHAR(75),
familia VARCHAR(30)
);

INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'N�O IDENTIFICADA','N�O IDENTIFICADA','N�O IDENTIFICADA');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'A�oita-cavalo','Luehea divaricata','Tiliaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Agua�','Chysophyllum marginatum','Sapotaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Alecrim','Holocalix balansae','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Angico-vermelho','Parapiptadenia rigida','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Ara��','Psidium catleyanum','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Ara��-do-mato','Myrceanthes gigantea','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Araticum','Rollinia rugulosa','Annonaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Araticum','Rollinia sylvatica','Annonaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Araticum-cag�o','Annona cacans','Annonaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Araticum-do-brejo','Annona glabra','Annonaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Araticum-salso','Rollinia salicifolia','Annonaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Aroeira-preta','Lithraea brasiliensis','Anacardiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Aroeira-salso','Schinus molle','Anacardiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Aroeira-vermelha','Schinus terebinthifolius','Anacardiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Bacopar�','Garcinia gardneriana','Clusiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Baga-de-macaco','Posoqueria acutifolia','Rubiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Baga��','Talauma ovata','Magnoliaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Batinga','Eugenia rostrifolia','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Bicu�ba','Virola oleifera','Myristicaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Bracatinga','Mimosa scabrella','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Branquilho','Sebastiania commersoniana','Euphorbiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Cabre�va','Myrocarpus frondosus','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Caixeta','Didimopanax morototonii','Araliaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Cambar�','Gochnatia polymorpha','Asteraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Camboat�-branco','Matayba eleagnoides','Sapindaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Camboat�-vermelho','Cupania vernalis','Sapindaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Camboim','Myrciaria delicatula','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Camboim','Myrciaria tenella','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Canaf�stula','Peltophorum dubim','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Cancorosa','Maytenus ilicifolia','Celastraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Cancorosa','Iodina rhombifolia','Satalaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Canela-amarela','Nectandra lanceolata','Lauraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Canela-do-brejo','Ocotea pulchella','Lauraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Canela-ferrugem','Nectandra mollis','Lauraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Canela-guaic�','Ocotea Puberula','Lauraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Canela-fedida','Nectandra megapotanica','Lauraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Canela-nho�ara','Nectandra membranaceae','Lauraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Canela-sassafraz','Ocotea odorifera','Lauraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Canjerana','Cabralea canjerana','Meliaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Capororoca','Myrsine umbellata','Myrsinaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Capororoca','Myrsine ferruginea','Myrsinaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Carne-de-vaca','Styrax leprusus','Styracaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Caroba','Jacaranda micrantha','Bignoniaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Carob�o','Pentapanax warmingiana','Araliaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Carvalho','Roupala brasiliensis','Proteaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Carvalho','Roupala pallida','Proteaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Carvalinho','Casearia decandra','Flacourtiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Casca-de-anta','Drimys brasiliensis','Winteraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'C�ssia-multijuga','Senna multijuga','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Ca�na','Ilex brevicuspis','Aquifoliaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Cedro','Cedrela fissilis','Meliaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Cerejeira','Eugenia involucrata','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Cerninho','Mosiera prysmatica','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Chal-chal','Allophylus edulis','Sapindaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Cina-cina','Parkinsonia aculeata','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Cincho','Sorocea bonplandii','Moraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Coc�o','Erythroxylum argentinum','Erythroxylaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Coronilha','Scutia buxifolia','Rhamnaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Corticeira-da-cerra','Erythrina falcata','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Corticeira-do-banhado','Erythrina crista-galli','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Dedaleira','Lafoensia pacari','Lythraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Emba�ba','Cecropia Pachystachia','Cecropiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Emba�ba','Cecropia glaziovi','Cecropiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Embiru��','Pseudobombax grandiflorum','Bombacaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Erva-de-bugre','Casearia silvestris','Flacourtiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Erva-mate','Ilex paraguariensis','Aquifoliaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Figueira','Ficus enormis','Moraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Figueira','Ficus luschnathiana','Moraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Figueira','Ficus monckii','Moraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Figueira','Ficus organensis','Moraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Goiabeira-serrana','Acca sellowiana','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Grapia','Apuleia leiocarpa','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Grindi�va','Trema micrantha','Ulmaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Grumixama','Eugenia brasiliensis','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guabij�','Myrcianthes pungens','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guabiroba','Campomanesia xanthocarpa','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guabirobinha','Campomanesia rhombea','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guajuvira','Patagonula americana','Boraginaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guamirim','Gomidesia palustris','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guamirim-da-folha-muida','Myrcia rostrata','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guaper�','Clethra scabra','Clethraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guaper�','Lamanonia ternata','Cunoniaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guapuruv�','Schizolobium parahyba','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guatamb�','Balfourodendron riedelianum','Rutaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Guatamb�-amarelo','Aspidosperma parvifolium','Apocynaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Imbuia','Ocotea porosa','Lauraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Ing�-beira-rio','Inga uruguensis','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Ing�-feij�o','Inga marginata','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Ing�-macaco','Inga sessilis','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Ip�-amarelo','Tabebuia chrsotricha','Bignoniaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Ip�-da-serra','Tabebuia alba','Bignoniaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Ip�-da-v�rzea','Tabebuia umbellata','Bignoniaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Ip�-roxo','Tabebuia heptaphylla','Bignoniaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Jaboticabeira','Plinia truciflora','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Jacarand�-branco','Machaerium paraguariense','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Jacatir�o','Miconia cinnamomifolia','Melastomataceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Jaracati�','Jacaratia quercifolia','Caricaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Licurana','Hieronyma alchorneoides','Euphorbiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Louro-mole','Cordia ecalyculata','Boraginaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Louro-pardo','Cordia trichotoma','Boraginaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Macuqueiro','Bathysa meridionalis','Rubiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Manac�-da-serra','Tibuchina mutabilis','Melastomataceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Maria-mole','Guapira opposita','Nyctaginaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Maria-preta','Diospyrus inconstans','Ebanaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Maric�','Mimosa bimucronata','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Marmeleiro-do-mato','Ruprechtia laxiflora','Polygonaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Mata-olho','Pachystroma longifolia','Euphorbiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Mata-olho','Pouteria salicifolia','Sapotaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Medalh�o-de-ouro','Cassia leptophylla','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Olho-de-cabra','Ormosia arborea','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Paineira','Chorisia speciosa','Bombacaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pata-de-vaca','Bauhinia forficata','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pau-de-morcego','Andira anthelmia','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pau-ferro','Astronium balansae','Anacardiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pau-jacar�','Piptadenia gonoacantha','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pau-leiteiro','Sapium glandulatum','Euphorbiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pau-toucinho','Piptocarpha angustifolia','Asteraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pessego-do-mato','Hexachlamys edulis','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pessegueiro-do-mato','Prunus sellowii','Rosaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pinda�ba','Xylopia brasiliensis','Annonaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pinheirinho','Podocarpus lambertii ','Podocarpaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pinheiro-brasileiro','Araucaria angustifolia','Araucariaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Pitangueira','Eugenia uniflora','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Rabo-de-bugio','Lonchocarpus campestris','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Rabo-de-bugio','Lonchocarpus muhlbergianus','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Sab�o-de-soldado','Quillaia brasiliensis','Rosaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Salgueiro','Salix humboldtiana','Salicaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Santa-rita','Gordonia fruticosa','Theaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Sapopema','Sloanea monosperma','Elaeocarpaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Sarandi-amarelo','Terminalia australis','Combretaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Sete-capotes','Campomanesia guazumifolia','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Sobraji','Colubrina glandulosa','Rhamnaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Sucar�','Gleditsia amorphoides','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Tajuva','Maclura tinctonia','Moraceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Tanheiro','Alchornea triplinervia','Euphorbiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Tapi�','Alchornea glandulosa','Euphorbiaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Tarum�','Vitex megapotamica','Verbanaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Timba�va','Enterolobium contortisiliquum','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Timb�','Ateleia glazioviana','Leguminosae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Tr�s-marias','Bougainvillea glabra','Nyctaginaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Trichilia','Trichilia clausseni','Meliaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Tucaneira','Cytharexylum myrianthum','Verbanaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Umb�','Phytolacca dioica','Phytolaccaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Uvaia','Eugenia pyriformis','Myrtaceae');
INSERT INTO arvore (chave, nomePopular, nomeCientifico, familia) values (NULL,'Vassour�o','Vernonia discolor','Asteraceae');

ALTER TABLE arvore ENABLE KEYS;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS bioma (
  chave int(5) NOT NULL auto_increment PRIMARY KEY,
  descricao varchar(50) NOT NULL
  );

LOCK TABLES bioma WRITE;
ALTER TABLE bioma DISABLE KEYS;
REPLACE INTO bioma (chave, descricao) VALUES (null,'AMAZ�NIA');
REPLACE INTO bioma (chave, descricao) VALUES (null,'COSTEIROS');
REPLACE INTO bioma (chave, descricao) VALUES (null,'CAATINGA');
REPLACE INTO bioma (chave, descricao) VALUES (null,'CERRADO');
REPLACE INTO bioma (chave, descricao) VALUES (null,'PANTANAL');
REPLACE INTO bioma (chave, descricao) VALUES (null,'MATA ATL�NTICA');
REPLACE INTO bioma (chave, descricao) VALUES (null,'PAMPA');
ALTER TABLE bioma ENABLE KEYS;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS epifita (
  chave int(5) NOT NULL auto_increment PRIMARY KEY,
  descricao varchar(50) NOT NULL
);

LOCK TABLES epifita WRITE;
ALTER TABLE epifita DISABLE KEYS;
REPLACE INTO epifita (chave, descricao) VALUES (null,'INEXISTENTE');
REPLACE INTO epifita (chave, descricao) VALUES (null,'DIVERSIDADE BAIXA');
REPLACE INTO epifita (chave, descricao) VALUES (null,'DIVERSIDADE M�DIA');
REPLACE INTO epifita (chave, descricao) VALUES (null,'DIVERSIDADE  ALTA');
ALTER TABLE epifita ENABLE KEYS;
UNLOCK TABLES;

CREATE TABLE IF NOT EXISTS vegetacao (
  chave int(5) NOT NULL auto_increment PRIMARY KEY,
  descricao varchar(50) NOT NULL
);


LOCK TABLES vegetacao WRITE;
ALTER TABLE vegetacao DISABLE KEYS;
REPLACE INTO vegetacao (chave, descricao) VALUES (null,'Floresta Ombr�fila Densa Atl�ntica');
REPLACE INTO vegetacao (chave, descricao) VALUES (null,'Floresta Ombr�fila Mista');
REPLACE INTO vegetacao (chave, descricao) VALUES (null,'Floresta Ombr�fila Aberta');
REPLACE INTO vegetacao (chave, descricao) VALUES (null,'Floresta Estacional Semidecidual');
REPLACE INTO vegetacao (chave, descricao) VALUES (null,'Floresta Estacional Decidual');
REPLACE INTO vegetacao (chave, descricao) VALUES (null,'Manguezal');
REPLACE INTO vegetacao (chave, descricao) VALUES (null,'Restinga');
REPLACE INTO vegetacao (chave, descricao) VALUES (null,'Campos de Altitude');
REPLACE INTO vegetacao (chave, descricao) VALUES (null,'Brejos Interioranos');
REPLACE INTO vegetacao (chave, descricao) VALUES (null,'Encraves Florestais do Nordeste');
ALTER TABLE vegetacao ENABLE KEYS;
UNLOCK TABLES;

CREATE TABLE IF NOT EXISTS estagio (
  chave int(5) NOT NULL auto_increment PRIMARY KEY,
  descricao varchar(30) NOT NULL
);

LOCK TABLES estagio WRITE;
ALTER TABLE estagio DISABLE KEYS; 
REPLACE INTO estagio (chave, descricao) VALUES (null,'INICIAL');
REPLACE INTO estagio (chave, descricao) VALUES (null,'M�DIO');
REPLACE INTO estagio (chave, descricao) VALUES (null,'AVAN�ADO');
REPLACE INTO estagio (chave, descricao) VALUES (null,'N�o Observado');
ALTER TABLE estagio ENABLE KEYS;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS especiesindicadoras (
  chave int(5) NOT NULL auto_increment PRIMARY KEY,
  nomeCientifico varchar(70) NOT NULL,
  nomeComum varchar(70) NOT NULL,
  vegetacao int(5) NOT NULL,
  estagio int(5) NOT NULL,
  FOREIGN KEY (vegetacao) REFERENCES vegetacao(chave),
  FOREIGN KEY (estagio) REFERENCES estagio(chave)
);

LOCK TABLES especiesindicadoras WRITE;
ALTER TABLE especiesindicadoras DISABLE KEYS;
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Pteridium aquilium','Samambaia-das-Taperas',1,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'hemicript�fitas Melinis minutiflora','Capim-gordura',1,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Andropogon bicornis','Capim-andaime, capim-rabo-de-burro',1,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'ten�fitas Biden pilosa','Pic�o-preto',1,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Solidago microglosa','Vara-de-foguete',1,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Baccharis elaeagnoides','Vassoura',1,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Baccharis dracunculifolia','Vassoura-braba',1,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Pteridium aquilium','Samambaia-das-Taperas',2,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Melines minutiflora','Capim-gordura',2,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Andropogon bicornis','Capim-andaime, capim-rabo-de-burro',2,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Biden pilosa','Pic�o-preto',2,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Solidago microglossa','Vara-de-foguete',2,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Baccharis elaeagnoides','Vassoura',2,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Baccharis dracunculifolia','Vassoura-braba',2,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Senecio brasiliensis','Flor-das-almas',2,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Cortadelia sellowiana','Capim-navalha, maceg�o',2,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Solnum erianthum','Fumo-bravo',2,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Pteridium aquilium','Samambaia-das-Taperas',5,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'hemicript�fitas Melinis minutiflora','Capim-gordura',5,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Andropogon bicornis','Capim-andaime, capim-rabo-de-burro',5,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Solidago microglosa','Vara-de-foguete',5,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Baccharis elaeagnoides','Vassoura',5,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Baccharis dracunculifolia','Vassoura-braba',5,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Senecio brasiliensis','Flor-das-almas',5,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Cortadelia sellowiana','Capim-navalha, maceg�o',5,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Solnum erianthum','Fumo-bravo',5,1);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'rapanea Ferruginea','Capororoca',1,2);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Dondonea viscosa','Vassoura-vermelha',1,2);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Cupanea vernalis','Cambot�-vermelho',2,2);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Schinus therebethifolius','Aroeira-vermelha',2,2);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Casearia silvestris','Cafezinho-do-mato',2,2);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Inga marginata','Inga feij�o',5,2);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Baunilha candicans','Pata-de-vaca',5,2);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Miconia cinnamomifolia','Jacatir�o-a�u',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Psychotria longipes','Caxeta',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Cecropia adenopus','Emba�ba',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Euterpe edulis','Palmiteiro',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Schizolobium parahiba','Guapuruvu',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Bathiza meridionalis','Macuqueiro',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Piptadenia gonoacantha','Pau-jacar�',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Hieronyma alchorneoides','Licurana',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Alchornea triplinervia','Tanheiro',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Nectandra leucothyrsus','Canela-branca',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Ocotea catharinensis','Canela-preta',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Euterpe edulis','Palmiteiro',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Talauma ovata','Bagua�u',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Chrysophylum viride','Aguai',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Aspidosperma olivaceum','Peroba-vermelha',1,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Ocotea puberula','Canela-guaica',2,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Piptocarpa angustifolia','Vassour�o-branco',2,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Vernonia discolor','Vassour�o-preto',2,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Mimosa scabrella','Bracatinga',2,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Ocotea puberula','Canela-guaica',5,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Alchornea triplinervia','Tanheiro',5,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Parapiptadenia rigida','Angico-vermelho',5,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Patagonula americana','Guajuvira',5,3);
REPLACE INTO especiesindicadoras (chave, nomeCientifico, nomeComum, vegetacao, estagio) VALUES (null,'Enterolobium contortisiliguum','Timbauva',5,3);
ALTER TABLE especiesindicadoras ENABLE KEYS;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS serrapilheira (
  chave int(5) NOT NULL auto_increment PRIMARY KEY,
  descricao varchar(30) NOT NULL
);


LOCK TABLES serrapilheira WRITE;
ALTER TABLE serrapilheira DISABLE KEYS;
REPLACE INTO serrapilheira (chave, descricao) VALUES (null,'INEXISTENTE');
REPLACE INTO serrapilheira (chave, descricao) VALUES (null,'FINA');
REPLACE INTO serrapilheira (chave, descricao) VALUES (null,'VARI�VEL');
REPLACE INTO serrapilheira (chave, descricao) VALUES (null,'ABUNDANTE');
ALTER TABLE serrapilheira ENABLE KEYS;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS trepadeira (
  chave int(5) NOT NULL auto_increment PRIMARY KEY,
  descricao varchar(50) NOT NULL
);


LOCK TABLES trepadeira WRITE;
ALTER TABLE trepadeira DISABLE KEYS;
REPLACE INTO trepadeira (chave, descricao) VALUES (null,'INEXISTENTE');
REPLACE INTO trepadeira (chave, descricao) VALUES (null,'HERB�CEAS');
REPLACE INTO trepadeira (chave, descricao) VALUES (null,'LENHOSAS');
ALTER TABLE trepadeira ENABLE KEYS;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS parcela (
  chave int(7) NOT NULL auto_increment PRIMARY KEY,
  coord1 varchar(25) default NULL,
  coord2 varchar(25) default NULL,
  coord3 varchar(25) default NULL,
  coord4 varchar(25) default NULL,
  area double default NULL,
  areaBasal double default NULL,
  vegetacao int(5) NOT NULL,
  bioma int(5) NOT NULL,  
  epifita int(5) NOT NULL,
  trepadeira int(5) NOT NULL,
  serrapilheira int(5) NOT NULL,
  muitasEspPioneiras char(1),
  subBosque char(1),
  dataExclusao date default NULL,  
  FOREIGN KEY (vegetacao) REFERENCES vegetacao(chave),
  FOREIGN KEY (bioma) REFERENCES bioma(chave),
  FOREIGN KEY (epifita) REFERENCES epifita(chave),
  FOREIGN KEY (trepadeira) REFERENCES vegetacao(chave),
  FOREIGN KEY (serrapilheira) REFERENCES serrapilheira(chave)
);



CREATE TABLE IF NOT EXISTS parcelaarvore (
  chave int(7) NOT NULL auto_increment PRIMARY KEY,
  chaveParcela int(7) NOT NULL,
  chaveArvore int(7) NOT NULL,
  cap double default 0.0,
  dap double default 0.0,
  altura double default 0.0,
  cas double default 0.0,
  distancia double default 0.0,
  areaBasal double default 0.0,
  FOREIGN KEY (chaveParcela) REFERENCES parcela(chave),
  FOREIGN KEY (chaveArvore) REFERENCES arvore(chave)
);

CREATE TABLE IF NOT EXISTS estado (
	uf char(2) NOT NULL PRIMARY KEY,
  	nome varchar(50) NOT NULL	
	);

LOCK TABLES estado WRITE;
ALTER TABLE estado DISABLE KEYS;
REPLACE INTO estado (uf, nome) VALUES ('OE','OUTROS ESTADOS');
REPLACE INTO estado (uf, nome) VALUES ('AC','Acre');
REPLACE INTO estado (uf, nome) VALUES ('AL','Alagoas');
REPLACE INTO estado (uf, nome) VALUES ('AM','Amazonas');
REPLACE INTO estado (uf, nome) VALUES ('AP','Amapa');
REPLACE INTO estado (uf, nome) VALUES ('BA','Bahia');
REPLACE INTO estado (uf, nome) VALUES ('CE','Cear�');
REPLACE INTO estado (uf, nome) VALUES ('DF','Distrito Federal');
REPLACE INTO estado (uf, nome) VALUES ('ES','Esp�rito Santo');
REPLACE INTO estado (uf, nome) VALUES ('GO','Goi�s');
REPLACE INTO estado (uf, nome) VALUES ('MA','Maranh�o');
REPLACE INTO estado (uf, nome) VALUES ('MG','Minas Gerais');
REPLACE INTO estado (uf, nome) VALUES ('MS','Mato Grosso do Sul');
REPLACE INTO estado (uf, nome) VALUES ('MT','Mato Grosso');
REPLACE INTO estado (uf, nome) VALUES ('PA','Par�');
REPLACE INTO estado (uf, nome) VALUES ('PB','Para�ba');
REPLACE INTO estado (uf, nome) VALUES ('PE','Pernambuco');
REPLACE INTO estado (uf, nome) VALUES ('PI','Piau�');
REPLACE INTO estado (uf, nome) VALUES ('PR','Paran�');
REPLACE INTO estado (uf, nome) VALUES ('RJ','Rio de Janeiro');
REPLACE INTO estado (uf, nome) VALUES ('RN','Rio Grande do Norte');
REPLACE INTO estado (uf, nome) VALUES ('RO','Rond�nia');
REPLACE INTO estado (uf, nome) VALUES ('RR','Ronaima');
REPLACE INTO estado (uf, nome) VALUES ('RS','Rio Grande do sul');
REPLACE INTO estado (uf, nome) VALUES ('SC','Santa Catarina');
REPLACE INTO estado (uf, nome) VALUES ('SE','Sergipe');
REPLACE INTO estado (uf, nome) VALUES ('SP','S�o Paulo');
REPLACE INTO estado (uf, nome) VALUES ('TO','Tocantins');

ALTER TABLE estado ENABLE KEYS;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS municipio (
	chave int(5) NOT NULL auto_increment PRIMARY KEY,
  	nome varchar(70) NOT NULL,
	uf char(2) NOT NULL,
	FOREIGN KEY (uf) REFERENCES estado(uf) 
	);


INSERT INTO municipio (chave, nome, uf) VALUES (null,  'OUTROS MUNIC�PIOS ' , 'OE');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Abdon Batista ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Abelardo Luz ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Agrol�ndia ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Agron�mica ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  '�gua Doce ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  '�guas de Chapec� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  '�guas Frias ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  '�guas Mornas ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Alfredo Wagner ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Alto Bela Vista ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Anchieta ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Angelina ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Anita Garibaldi ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Anit�polis ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ant�nio Carlos ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Api�na ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Arabut� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Araquari ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ararangu� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Armaz�m ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Arroio Trinta ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Arvoredo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ascurra ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Atalanta ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Aurora ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Balne�rio Arroio do Silva ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Balne�rio Barra do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Balne�rio Cambori� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Balne�rio Gaivota ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bandeirante ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Barra Bonita ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Barra Velha ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bela Vista do Toldo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Belmonte ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Benedito Novo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bigua�u ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Blumenau ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bocaina do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bom Jardim da Serra ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bom Jesus ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bom Jesus do Oeste ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bom Retiro ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bombinhas ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Botuver� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bra�o do Norte ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Bra�o do Trombudo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Brun�polis ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Brusque ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ca�ador ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Caibi ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Calmon ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Cambori� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Campo Alegre ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Campo Belo do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Campo Er� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Campos Novos ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Canelinha ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Canoinhas ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Cap�o Alto ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Capinzal ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Capivari de Baixo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Catanduvas ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Caxambu do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Celso Ramos ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Cerro Negro ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Chapad�o do Lageado ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Chapec� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Cocal do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Conc�rdia ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Cordilheira Alta ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Coronel Freitas ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Coronel Martins ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Correia Pinto ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Corup� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Crici�ma ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Cunha Por� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Cunhata� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Curitibanos ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Descanso ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Dion�sio Cerqueira ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Dona Emma ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Doutor Pedrinho ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Entre Rios ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ermo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Erval Velho ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Faxinal dos Guedes ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Flor do Sert�o ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Florian�polis ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Formosa do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Forquilhinha ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Fraiburgo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Frei Rog�rio ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Galv�o ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Garopaba ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Garuva ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Gaspar ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Governador Celso Ramos ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Gr�o Par� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Gravatal ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Guabiruba ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Guaraciaba ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Guaramirim ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Guaruj� do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Guatambu ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Herval d\'Oeste ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ibiam ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ibicar� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ibirama ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'I�ara ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ilhota ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Imaru� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Imbituba ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Imbuia ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Indaial ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Iomer� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ipira ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ipor� do Oeste ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ipua�u ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ipumirim ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Iraceminha ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Irani ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Irati ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Irine�polis ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'It� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Itai�polis ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Itaja� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Itapema ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Itapiranga ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Itapo� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ituporanga ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Jabor� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Jacinto Machado ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Jaguaruna ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Jaragu� do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Jardin�polis ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Joa�aba ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Joinville ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Jos� Boiteux ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Jupi� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Lacerd�polis ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Lages ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Laguna ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Lajeado Grande ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Laurentino ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Lauro M�ller ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Lebon R�gis ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Leoberto Leal ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Lind�ia do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Lontras ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Lu�s Alves ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Luzerna ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Macieira ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Mafra ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Major Gercino ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Major Vieira ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Maracaj� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Maravilha ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Marema ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Massaranduba ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Matos Costa ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Meleiro ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Mirim Doce ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Modelo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Monda� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Monte Carlo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Monte Castelo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Morro da Fuma�a ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Morro Grande ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Navegantes ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Nova Erexim ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Nova Itaberaba ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Nova Trento ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Nova Veneza ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Novo Horizonte ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Orleans ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Otac�lio Costa ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ouro ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ouro Verde ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Paial ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Painel ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Palho�a ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Palma Sola ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Palmeira ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Palmitos ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Papanduva ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Para�so ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Passo de Torres ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Passos Maia ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Paulo Lopes ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Pedras Grandes ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Penha ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Peritiba ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Petrol�ndia ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Pi�arras ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Pinhalzinho ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Pinheiro Preto ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Piratuba ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Planalto Alegre ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Pomerode ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ponte Alta ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ponte Alta do Norte ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Ponte Serrada ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Porto Belo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Porto Uni�o ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Pouso Redondo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Praia Grande ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Presidente Castelo Branco ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Presidente Get�lio ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Presidente Nereu ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Princesa ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Quilombo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Rancho Queimado ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Rio das Antas ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Rio do Campo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Rio do Oeste ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Rio do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Rio dos Cedros ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Rio Fortuna ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Rio Negrinho ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Rio Rufino ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Riqueza ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Rodeio ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Romel�ndia ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Salete ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Saltinho ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Salto Veloso ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Sang�o ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Santa Cec�lia ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Santa Helena ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Santa Rosa de Lima ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Santa Rosa do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Santa Terezinha ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Santa Terezinha do Progresso ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Santiago do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Santo Amaro da Imperatriz ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Bento do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Bernardino ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Bonif�cio ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Carlos ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Crist�v�o do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Domingos ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Francisco do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Jo�o Batista ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Jo�o do Itaperi� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Jo�o do Oeste ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Jo�o do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Joaquim ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Jos� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Jos� do Cedro ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Jos� do Cerrito ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Louren�o do Oeste ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Ludgero ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Martinho ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Miguel da Boa Vista ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Miguel do Oeste ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'S�o Pedro de Alc�ntara ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Saudades ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Schroeder ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Seara ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Serra Alta ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Sider�polis ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Sombrio ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Sul Brasil ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Tai� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Tangar� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Tigrinhos ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Tijucas ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Timb� do Sul ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Timb� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Timb� Grande ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Tr�s Barras ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Treviso ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Treze de Maio ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Treze T�lias ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Trombudo Central ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Tubar�o ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Tun�polis ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Turvo ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Uni�o do Oeste ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Urubici ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Urupema ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Urussanga ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Varge�o ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Vargem ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Vargem Bonita ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Vidal Ramos ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Videira ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Vitor Meireles ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Witmarsum ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Xanxer� ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Xavantina ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Xaxim ' , 'SC');
INSERT INTO municipio (chave, nome, uf) VALUES (null,  'Zort�a ' , 'SC');


CREATE TABLE IF NOT EXISTS pessoa (
  chave int(7) NOT NULL auto_increment PRIMARY KEY,
  nome varchar(70) NOT NULL,
  dataNasc date,
  rg varchar(25) default NULL,
  cpf varchar(14) default NULL,
  endereco varchar(30) default NULL,
  numero varchar(10) default NULL,
  bairro varchar(30) default NULL,
  municipio int(5) NOT NULL,
  telRes varchar(15) default NULL,
  telCom varchar(15) default NULL,
  telCel varchar(15) default NULL,
  eMail varchar(30) default NULL,
  elaborador char(1),
  dataExclusao date,
  FOREIGN KEY (municipio) REFERENCES municipio(chave)
);


CREATE TABLE IF NOT EXISTS projeto (
  chave int(7) NOT NULL auto_increment PRIMARY KEY,
  data date default NULL,
  endereco varchar(50) default NULL,
  numero varchar(10) default NULL,
  bairro varchar(30) default NULL,
  municipio int(5) NOT NULL,
  coord1 varchar(25) default NULL,
  coord2 varchar(25) default NULL,
  coord3 varchar(25) default NULL,
  coord4 varchar(25) default NULL,
  areaTotal double default NULL,
  avaliacao char(1),
  estagio int(5) NOT NULL,
  areaDegradada double default NULL,
  degradacaoEmAPP char(1),
  proprietario int(5) NOT NULL,
  elaborador int(5) NOT NULL,
  dataExclusao date,
  FOREIGN KEY (municipio) REFERENCES municipio(chave),	
  FOREIGN KEY (estagio) REFERENCES estagio(chave),
  FOREIGN KEY (proprietario) REFERENCES pessoa(chave),
  FOREIGN KEY (elaborador) REFERENCES pessoa(chave)
);


CREATE TABLE IF NOT EXISTS projetoparcela (
  chave int(7) NOT NULL auto_increment PRIMARY KEY,
  chaveProjeto int(7) NOT NULL,
  chaveParcela int(7) NOT NULL,
  FOREIGN KEY (chaveProjeto) REFERENCES projeto(chave),	
  FOREIGN KEY (chaveParcela) REFERENCES parcela(chave)  
 );
