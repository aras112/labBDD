package edu.iis.mto.bdd.trains.junit;

import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.ItineraryServiceImpl;
import edu.iis.mto.bdd.trains.services.TimetableService;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WhenCalculatingArrivalTimes
    {
    @Mock
    private TimetableService timetableService;
    private Line line;
    private ItineraryServiceImpl intineraryService;

    @Before
    public void setUp() throws Exception
        {
        line = Line.named("Western").departingFrom("Emu Plains").withStations("Emu Plains",
                "Parramatta", "Town Hall",
                "North Richmond");
        intineraryService = new ItineraryServiceImpl(timetableService);


        when(timetableService.findLinesThrough("Emu Plains", "North Richmond")).thenReturn(new LinkedList<Line>()
            {{
            add(line);
            }});
        }

    @Test
    public void shouldReturnTimeForEmuPlainsStation()
        {
        //g

        when(timetableService.findLinesThrough("Emu Plains", "North Richmond")).thenReturn(new LinkedList<Line>()
            {{
            add(line);
            }});
        when(timetableService.findArrivalTimes(line, "Emu Plains")).thenReturn(new LinkedList<LocalTime>()
            {{
            add(LocalTime.parse("10:10"));
            }});
        when(timetableService.findArrivalTimes(line, "North Richmond")).thenReturn(new LinkedList<LocalTime>()
            {{
            add(LocalTime.parse("12:10"));
            }});
        //w
        LocalTime localTime = intineraryService.findNextDepartures("Emu Plains", "North Richmond"
                , "Western", LocalTime.parse("10:00"));
        //t

        Assert.assertThat(localTime, is(LocalTime.parse("12:10")));
        }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionBecauseOfBadTime()
        {
        //g
        when(timetableService.findArrivalTimes(line, "Emu Plains")).thenReturn(new LinkedList<LocalTime>()
            {{
            add(LocalTime.parse("10:10"));
            }});
        when(timetableService.findArrivalTimes(line, "North Richmond")).thenReturn(new LinkedList<LocalTime>()
            {{
            add(LocalTime.parse("12:10"));
            }});
        //w
        LocalTime localTime = intineraryService.findNextDepartures("Emu Plains", "North Richmond"
                ,"Western", LocalTime.parse("11:00"));
        //t throw
        }
    }
