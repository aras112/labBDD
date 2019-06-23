# language: pl
Funkcja: Informacja dla podróżnych o czasie przybycia do stacji docelowej
  W celu bardziej efektywnego planowania podróży
  Jako podróżny
  Chcę wiedzieć, o której godzinie dotrę do stacji docelowej

  Szablon scenariusza: Szacowanie czasu przyjazdu
    Zakładając chcę się dostać z "<misto poczatkowe>" do "<misto docelowe>"
    I następny pociąg odjeżdża o "<czas odjazdu>" na linii "<nazwa lini>"
    Gdy zapytam o godzinę przyjazdu
    Wtedy powinienem uzyskać następujący szacowany czas przyjazdu: "<czas przyjazdu>"
    Przykłady:
      | misto poczatkowe | misto docelowe | czas odjazdu | nazwa lini | czas przyjazdu
      | Epping           | Central        | 8:03         | Northern   | 8:48           |
      | Epping           | Central        | 8:07         | Newcastle  | 8:37           |

