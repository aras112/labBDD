package edu.iis.mto.bdd.trains.cucumber.steps;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.LocalTime;
import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.ItineraryServiceImpl;
import edu.iis.mto.bdd.trains.services.TimetableService;

public class OptimalItinerarySteps
    {
    private String departure;
    private String destination;
    private LocalTime time;
    private String lineName;
    TimetableService timetableService = new InMemoryTimetableService();
    ItineraryServiceImpl itineraryService = new ItineraryServiceImpl(timetableService);
    List<LocalTime> localTimes = new ArrayList<>();

    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
                                    @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes)
        {

        this.departure = departure;
        this.destination = destination;
        timetableService.setUniversalDepartureTimes(ImmutableList.copyOf(departureTimes));
        List<Line> lines = timetableService.findLinesThrough(departure, destination);
        Assert.assertNotNull("nie ma takiego polczenia", lines);
        Assert.assertFalse(lines.isEmpty());

        }

    @Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination,
                                  @Transform(JodaLocalTimeConverter.class) LocalTime startTime)
        {
        LocalTime temp = itineraryService.findNextDepartures(departure, destination, "Western",
                startTime.plusMinutes(1));

        while (temp.isBefore(startTime.plusMinutes(15)))
            {
            localTimes.add(new LocalTime(temp));
            temp = itineraryService.findNextDepartures(departure, destination, "Western",
                    temp.plusMinutes(1));
            }

        }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes)
        {
        Assert.assertTrue(expectedTrainTimes.equals(localTimes));
        }
    }
