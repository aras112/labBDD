# language: pl
Funkcja: Informacja dla podróżnych o czasie przybycia do stacji docelowej
  W celu bardziej efektywnego planowania podróży
  Jako podróżny
  Chcę wiedzieć, o której godzinie dotrę do stacji docelowej

  Szablon scenariusza: Szacowanie czasu przyjazdu
    Zakładając chcę się dostać z "<mistoPoczatkowe>" do "<mistoDocelowe>"
    I następny pociąg odjeżdża o "<czasOdjazdu>" na linii "<nazwaLini>"
    Gdy zapytam o godzinę przyjazdu
    Wtedy powinienem uzyskać następujący szacowany czas przyjazdu: "<czasPrzyjazdu>"
    Przykłady:
      | mistoPoczatkowe | mistoDocelowe | czasOdjazdu | nazwaLini | czasPrzyjazdu |
      | Epping          | Central       | 8:03        | Northern  | 8:48          |
      | Epping          | Central       | 8:07        | Newcastle | 8:37          |

