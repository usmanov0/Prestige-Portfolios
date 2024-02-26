package com.example.prestigeportfoliocreators.service;

import com.example.prestigeportfoliocreators.models.Stats;
import com.example.prestigeportfoliocreators.repository.StatsRepository;
import com.example.prestigeportfoliocreators.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatsService {
    @Autowired
    private StatsRepository statsRepo;

    public Stats getStats(){
        Optional<Stats> optionalStats = statsRepo.findById(1L);
        if (!optionalStats.isPresent()){
            Stats stats = new Stats(1, DateFormat.MMddyyyy(),0L);
            statsRepo.save(stats);
            return stats;
        }
        return optionalStats.get();
    }

    public Stats updateLastUpdate(){
        String date = DateFormat.MMddyyyy();
        Stats stats = getStats();
        stats.setDate(date);
        return statsRepo.save(stats);
    }

    public Stats updateViews(){
        Stats stats = getStats();
        stats.setViews(stats.getViews()+1);
        return statsRepo.save(stats);
    }
}
