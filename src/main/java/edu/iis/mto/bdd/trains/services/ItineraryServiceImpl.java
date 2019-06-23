package edu.iis.mto.bdd.trains.services;

import org.joda.time.LocalTime;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import edu.iis.mto.bdd.trains.model.Line;

public class ItineraryServiceImpl implements ItineraryService
    {
    TimetableService timetableService;
    Integer trainNumber = 0;

    public ItineraryServiceImpl(TimetableService timetableService)
        {
        this.timetableService = timetableService;
        }

    @Override
    public LocalTime findNextDepartures(String departure, String destination,
                                        String lineName, LocalTime time)
        {
        List<Line> lineList = timetableService.findLinesThrough(departure, destination);
        List<Line> correctLine = getLinesWithCorrectName(lineName, lineList);

        LocalTime theBestTime = null;

        for (Line line : correctLine)
            {
            LocalTime theBestArrivalTimeInThisLine = findTheBestTimeForCity(departure, time, line);

            if (theBestArrivalTimeInThisLine != null)
                {
                trainNumber--;/*if train number 3 is delayed you can choose number 4 */
                LocalTime tempResultTime = findTheBestTimeForCity(destination, theBestArrivalTimeInThisLine, line);

                if (newTimeIsBetter(theBestTime, tempResultTime))
                    {
                    theBestTime = tempResultTime;
                    }
                }
            }

        return theBestTime;
        }

    private List<Line> getLinesWithCorrectName(String lineName, List<Line> lineList)
        {
        lineList = lineList.stream().filter(line -> line.getLine().equals(lineName)).collect(Collectors.toList());
        return lineList;
        }

    private boolean newTimeIsBetter(LocalTime theBestTime, LocalTime tempTime)
        {
        return theBestTime == null || theBestTime.isAfter(tempTime);
        }

    private LocalTime findTheBestTimeForCity(String departure, LocalTime time, Line line)
        {
        return timetableService.findArrivalTimes(line, departure)
                .stream()
                .skip(trainNumber)
                .filter(localTime ->
                {
                trainNumber++;
                return localTime.isAfter(time) || localTime.equals(time);
                })
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
        }
    }
