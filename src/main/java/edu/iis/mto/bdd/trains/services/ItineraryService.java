package edu.iis.mto.bdd.trains.services;

import org.joda.time.LocalTime;

import java.util.List;

public interface ItineraryService
    {
    LocalTime findNextDepartures(String departure, String destination,
                                 String lineName, LocalTime time);


    }
