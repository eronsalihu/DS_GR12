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

--Komanda Playfair--
  Playfair cipher perdore matrice/tabele(5x5) e cila permban celesin.Per me gjeneru celesin duhet qe hapsirat ne tabele te mbushen me shkronja e celsit duke i injoruar shkonjat duplikate, pastaj mbushet me shkronjat tjera te mbetura ne baze te alfabetit. Table permban gjithsej 25 karaktere(shkronja) prandaj shkonja "J" dhe ajo "I" vendosen ne te njejtin vend ose 𝒓𝒆𝒑𝒍𝒂𝒄𝒆𝑨𝒍𝒍("𝑱","𝑰").
Per ta enkriptu mesazhin e dhene do t'duhej qe mesazhi i dhene te ndahej ne nga dy letra(grupe). Enkriptimi i mesazhit kerkon qe teksti i mesazhit te jete me gjatesi cift keshtuqe, nese mesazhi eshte tek at'here i shtohet nje shkronje ("X") per ta kompletu diagramin e perbere me nga dy shkronja.
  Enkriptimi i mesazhit behet duke ndjekur disa rregulla qe qojne deri te ciphertext-i:
  Nese shkonjat jane te njejta ("ALKOOL") at'her vendoset "X" mes atyre dy shkonjave.
  --
  Nese te dy shkronjat jane ne te njejten kolone, merret shkronja poshte seciles.
  --
  Nese te dy shkronjat jane ne te njejten rreshte, merret shkronja ne te djathte te seciles.
  --
  Nese asnjera prej rregullave t'mesiperme nuk vlene, at'here formohet nje drejtkëndesh me dy shkronja dhe merrnet shkronja ne kendin e kundert horizontal te drejtkendeshit.
  --
  Per me dekriptu eshte pothuajse e njejte me enkriptimin veq duke bere te kunderten.Dekriptimi duhet t'kete celesin e njejte dhe gjeneron tablen(5x5) e njejte dhe dekripton mezashin e dhene ne baze te atij celesi(*Per enkriptim dhe dekriptim duhet perdoret i njejti celes*)
  Per dekriptim vlejn te gjitha kushtet qe jane permendur te enkriptimi.
  Source Code(https://github.com/northgc/playfair/blob/c9355592313a4454cc159b89d87f788018357663/playfair.java)
