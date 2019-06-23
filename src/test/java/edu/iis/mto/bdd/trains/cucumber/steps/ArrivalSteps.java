package edu.iis.mto.bdd.trains.cucumber.steps;

import org.joda.time.LocalTime;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import edu.iis.mto.bdd.trains.cucumber.steps.JodaLocalTimeConverter;

public class ArrivalSteps
    {
    @Zakładając("^chcę się dostać z \"([^\"]*)\" do \"([^\"]*)\"$")
    public void chcęSięDostaćZDo(String departure, String destination) throws Throwable
        {
        // Write code here that turns the phrase above into concrete actions
        }

    @I("^następny pociąg odjeżdża o \"([^\"]*)\" na linii \"([^\"]*)\"$")
    public void następnyPociągOdjeżdżaONaLinii(@Transform(JodaLocalTimeConverter.class) LocalTime time,
                                               String line) throws Throwable
        {
        // Write code here that turns the phrase above into concrete actions

        }

    @Gdy("^zapytam o godzinę przyjazdu$")
    public void zapytamOGodzinęPrzyjazdu()
        {

        }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: \"([^\"]*)\"$")
    public void powinienemUzyskaćNastępującySzacowanyCzasPrzyjazdu(@Transform(JodaLocalTimeConverter.class) LocalTime time) throws Throwable
        {
        // TODO: 23.06.2019
        //  Implementację kryteriów akceptacji rozpoczynaj od kroku @Then
        //  definiując oczekiwany rezultat. W przypadku tego scenariusza będzie to
        //  sprawdzenie czy zaproponowane (przez usługę planowania podróży)
        //  godziny są zgodne z oczekiwanymi.
        }
    }
