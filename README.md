# DS_Gr12
--Komanda Vigenere--
  Krijojme metoden e vigenere per encrypt me dy parametra formal String key,String plaintext e cila bazohet
ne jepjen e nje teksti dhe qelesit mbi te cilen do krijohet nje ciphertext 
per arsye se qelesi nuk eshte i gjate sa eshte plaintexti ateher e fusim mbrenda nje forloop-e 
e cila e perserit key-n e jepur derisa te behet sa gjatesia e plaintextit.
Pastaj krijojme nje forloop tjeter e cila shikon karakter per karakter se sipas permutacionit
e shikon distancen mes shkronjave perkatese dhe distancen mes tyre per te treguar se per sa do 
rritet shkronja ne pozicionin e duhur.Per shkak se key eshte bere per tu perseritur ne gjatesin e plaintextit mbi te cilen ne ate 
gjatesi numerohen edhe space numrat dhe karakteret e tjera kemi krijuar nje counter i cili numeron space
numrat dhe qdo karakter jashte rangut A-Z rang i cili perfshin qdo karakter jashte
shkronjave te medhaja dhe te vogla me te cilen e bejme minus ate counter per te krahasuar shkronjat ne
poziten perkatese ne plaintext dhe atyre ne key dallim i cili llogaritet me counter.
Logjika per te kthyer karakterin i cili duhet eshte gjetja e distances mes dy shkronjave 𝒃=𝒌𝒆𝒚.𝒄𝒉𝒂𝒓𝑨𝒕(𝒊-𝒄)-'𝒂';
dhe shkronja e cila duhet eshte 𝒄𝒉=(𝒄𝒉𝒂𝒓)(𝒑𝒍𝒂𝒊𝒏𝒕𝒆𝒙𝒕.𝒄𝒉𝒂𝒓𝑨𝒕(𝒊)+𝒃);
e shikojm shkronjen nese eshte me e vogel se z nese jo shkojm me logjiken e modulos.
Nenkuptojme se secili karakter i gjetur shtohet ne vargun e karaktereve apo stringun e krijuar
dhe kemi thene qe per qdo karakter i cili nuk eshte shkronje por simbol tjeter vetem pershkruhet.Hapat perseriten per shkronja
te medha si dhe per te vogla.
//Perseritja e qelesit per encrypt dhe decrypt ne Vigenere eshte marre nga GeekForGeeks;
