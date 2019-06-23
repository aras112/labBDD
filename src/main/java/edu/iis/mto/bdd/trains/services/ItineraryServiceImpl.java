package edu.iis.mto.bdd.trains.services;

import org.joda.time.LocalTime;

import edu.iis.mto.bdd.trains.model.Line;

public class ItineraryServiceImpl implements ItineraryService
    {
    TimetableService timetableService;

    public ItineraryServiceImpl(TimetableService timetableService)
        {
        this.timetableService = timetableService;
        }

    @Override
    public LocalTime findNextDepartures(String departure, String destination, LocalTime time)
        {
        return null;
        }
    }
