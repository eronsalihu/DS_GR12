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
 - Nese shkonjat jane te njejta ("ALKOOL") at'her vendoset "X" mes atyre dy shkonjave.
 - Nese te dy shkronjat jane ne te njejten kolone, merret shkronja poshte seciles.
 - Nese te dy shkronjat jane ne te njejten rreshte, merret shkronja ne te djathte te seciles.
 - Nese asnjera prej rregullave t'mesiperme nuk vlene, at'here formohet nje drejtkëndesh me dy shkronja dhe merrnet shkronja ne kendin e kundert horizontal te drejtkendeshit.
 Per me dekriptu eshte pothuajse e njejte me enkriptimin veq duke bere te kunderten.Dekriptimi duhet t'kete celesin e njejte dhe gjeneron tablen(5x5) e njejte dhe dekripton mezashin e dhene ne baze te atij celesi(*Per enkriptim dhe dekriptim duhet perdoret i njejti celes*)
  Per dekriptim vlejn te gjitha kushtet qe jane permendur te enkriptimi.
  Source Code(https://github.com/northgc/playfair/blob/c9355592313a4454cc159b89d87f788018357663/playfair.java)


--Komanda Permutacion--
~Enkriptimi~
Fillimisht krijojme metoden e permutacion_encrypt me dy parametra formal string key dhe sting plaintext .
Fazat per enkriptimin qe duhet kaluar vijojne ne kete menyre:
1.Fillimisht  mirret plaintexti  dhe e ndajme ate ne baze te key ne kete rast kemi key=4 domethene plaintextin e ndajme si array me nga 4 karaktere ne kete rast kemi perdorur funksionin split(source:Stakoverflow)
2.Nese ne array-at e krijuar  gjatesia e plaintexti te ndare eshte me e vogel sesa  gjatesia e key atehere te gjitha hapsirat array-at qe kan gjatesi me te vogel mbushen me ndonje shkronje ne kete rast ne kemi perdorur shkronjen "w" deri ateher kur gjatesia e plantextit te jete e barabarte me  gjatesine e key-it dhe tasti "space" mbushet me karakterin "x".
3.Pas mbushjes se hapsirave te zbrazeta merremi me enkriptimin e te dhenave .
4.Pastaj secili karakter vendoset ne poziten e caktuar ne  baze te key te dhene duke e bere kalimin nga string ne integer ne menyre qe secili karakter te kaloj ne poziten e caktuar te paraqitur ne key-in e dhene .
The se fundmi thirrim funksionin e enkriptimin peer te paraqitu ciphertextin e kerkuar.
~Dekriptimi~
Fillimisht krijojme metoden e permutacion_decrypt me dy parametra formal string key dhe sting encrypted(apo ciphertexti) .
Fazat per dekriptimin e te dhenave jane:
1.Ciphertexti e ndajme ne baze te key-it te dhene me te funksionit split().
2.Pas ndarjet se ciphetextit  marrim ate dhe si secilin karakter e vendosim ne poziten e key-it te caktuar.
3.Pastaj  shikojm nese ne ciphertext kemi ndonje "w" i cili eshte i vendosur vetem ne rast te hapsirave e zevendesojm me arrayin '\u0000'(source: stackoverflow) i cili paraqet empy character por nese "w"  nuk eshte vetem hapsire e cila eshte mbushur ne enkriptim (dmth eshte karakter ne plaintext) ateher mbetet e pazevendesuar.
Perfundimisht thirrim funksionin i cili shfaq  plaintextin e kerkuar.


--Metoda Create User--
Kryesisht ka te beje me gjenerimin e nje qelesi privat dhe publik RSA dhe ruajten e tyre ne XML files me extension
te qelesit privat .xml kurse tek qelsi publik ekstensionit xml i shtohet nje oub .pu.xml...E vetmja veshtiresi qe hasa
ne kete projekt ishte shkruajtja e nje xml file nga modeli DOM.Kompajllimi ne cmd eshte i thjesht pra se pari futemi 
ne pathin perkates dhe bejme debug javac createUser.java
Pastaj e bejme run me dhenien e argumenteve perkatese java ds create-user dhe emrin se si deshirojm ta ruajme e cila pranon
vetem shkronja te alfabetit anglez numra dhe underline. Komanda eshte zgjeruar ashte qe gjate krijimit te shfrytezuesit kerkohet 
dhe fjalekalimi. Fjalekalimi kerkohet permes inputit qe te mos figuroj ndonje e dhene e tij ne histori. Fjalekalimi duhet te perbushe
kriterin prej 6 karaktereve e me shume, te permbaje se paku nje simbol apo nje numer. Te dhenat e shkfrytezuesi ruhen ne nje databaze 
dhe fjalkalimi encriptohet me hash dhe salt.
Feedbacks nga geeksforgeeks dhe stackoverflow

--Metoda Delete User--
Shikon nese filet me emrin perkates te dhene ekzistojne ai publik dhe privat dhe i fshine ato me file.delete()
Kompajllimi me deleteUser.java kurse run me ane te argumenteve java ds delete-user emri.
Feedbacks w3schools.

--ExportKey--
Eksporton çelësin publik ose privat të shfrytëzuesit nga direktoriumi i çelësave. Komanda ka dy argumente,
argumenti i pare <𝒑𝒖𝒃𝒍𝒊𝒄|𝒑𝒓𝒊𝒗𝒂𝒕𝒆> e përcakton llojin e çelësit që eksportohet.
Argumenti i dyte <𝒏𝒂𝒎𝒆> e përcakton çelësin e cilit shfrytëzues të eksportohet.
Argumenti opsional [𝒇𝒊𝒍𝒆] e përcakton shtegun e fajllit se ku do të ruhet çelësi i eksportuar. Nëse mungon argumenti 
atëherë çelësi do të shfaqet në console.

--ImportKey--
Shikon per nje qeles me  ne shtegun e dhene dhe fajllin e percaktuar dhe e ruan ate ne filen me emrin e dhene ne 
argumentin e dyte ne cmd.Nga permbajtja mbrenda filev sipas nodes apo elementeve e din se ai file a eshte privat apo 
publik dhe ku duhet ruajtur.Gjithashtu nese eshte http https merr trupin e URL.Kompajllimin me javac importKey.java
si dhe run me ane te 3 argumenteve me kete metode java ds import-key emri dhe pathi.
Feedbacks ndihmese nga create user me eksplorimin ne xml file si dhe nga stackoverflow.

--Login--
Komanda 'login' testin kyqjen shfrytëzues/fjalëkalim. Në rast suksesi lëshohet një token i nënshkruar i cili mund të
përdoret për autentikim të shfrytëzuesit. Nënshkrim të tokenit të leshuar behet me çelës private te shfrytëzuesit, ndërsa për vërtetim të nënshkrimit përdoret çelësi publik i shfrytëzuesit. Tokeni skadon pas 20 minutave. Tokeni mund të përdoret vetëm për shfrytëzuesin për të cilin është lëshuar.

--Status--
Jep informata rreth tokenit te leshuar.Nëse tokeni ka skaduar, nuk ka nënshkrim valid, ose nuk ekziston shfrytëzuesi, 
atëherë tokeni nuk konsiderohet valid.

--Write-Message--
Ka te beje me enkriptimin e tekstit qe deshirojme me ane te Desit.Ku se pari ruajm emrin ne base64 pastaj edhe krijimin
e 8 secure random bytes ne iv te koduara ne base64.Pastaj keyn e gjeneruar nga random 8 bytes te cilin e shendrrojme ne secure
key e enkriptojme me qelesin publik nga argumenti i 2te ne cmd personit qe i takon dhe pastaj me keyn origjinal me des e enkriptojme
fjaline qe deshirojme ne argumentin e 3te.Kompajllimi me java writeMessage.java berja run nga java ds write-message emri(ku marrim qelsin publik) teksti(qe deshirojme te enkriptojme) dhe nje argument opsional [file] nese nuk deshirojm ta shfaqim ne konzole por ta ruajm ne file. Komanda eshte zgjeruar ashte qe te pranoj opsionit --𝒔𝒆𝒏𝒅𝒆𝒓 <𝒕𝒐𝒌𝒆𝒏>.Vlera sender është emri i shfrytëzuesit që i korrespondon tokenit token. Komanda dështon nëse tokeni nuk është valid ose ka skaduar. Nëse validohet tokeni me sukses, atëherë nënshkrimi bëhet me çelësin privat të dërguesit 𝒔𝒆𝒏𝒅𝒆𝒓.

--Read-Message--
E dekripton dhe e shfaq në console mesazhin e enkriptuar. Argumenti <𝒆𝒏𝒄𝒓𝒚𝒑𝒕𝒆𝒅-𝒎𝒆𝒔𝒔𝒂𝒈𝒆> paraqet mesazhin e enkriptuar sipas skemës së komandës 𝒘𝒓𝒊𝒕𝒆-𝒎𝒆𝒔𝒔𝒂𝒈𝒆. Nëse ky argument nuk përputhet me skemën e enkriptimit atëherë të provohet të lexohet argumenti si shteg i fajllit në të cilin gjendet mesazhi. Emri i 𝒔𝒉𝒇𝒓𝒚𝒕𝒆̈𝒛𝒖𝒆𝒔𝒊𝒕/𝒄̧𝒆𝒍𝒆̈𝒔𝒊𝒕 dekodohet nga mesazhi. Kuptohet që për ta dekriptuar mesazhin nevojitet çelësi privat i shfrytëzuesit. Nëse mungon ky çelës do të shfaqet një mesazh gabimi. Nëse figuron pjesa e 𝒅𝒆̈𝒓𝒈𝒖𝒆𝒔𝒊𝒕/𝒏𝒆̈𝒏𝒔𝒉𝒌𝒓𝒊𝒎𝒊𝒕 në mesazh, atëherë do të tentohet verifikimi i atij nënshkrimi duke përdorur çelësin publik të dërguesit.

Source:
**https://stackoverflow.com/questions/38939056/jjwt-generated-token-has-an-invalid-signature
https://dzone.com/articles/add-secure-token-authentication-to-your-java-app
https://developer.okta.com/blog/2018/10/31/jwts-with-java
https://github.com/jwtk/jjwt
https://jwt.io/ **
-- logjika e marre per ndertimin e tokenit



