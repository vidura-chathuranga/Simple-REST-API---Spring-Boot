package dev.vidura.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private final List<Run> runs = new ArrayList<>();

    List<Run> getAllRuns() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream().filter(run -> run.id().equals(id)).findFirst();
    }
    @PostConstruct
    private void init() {

        runs.add(new Run(
                1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                Location.INDOOR
        ));

        //add second run object
        runs.add(new Run(
                2,
                "Wednesday Evening Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                6,
                Location.OUTDOOR
        ));
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id){
        Optional<Run> existingRun = findById(id);

        if(existingRun.isPresent()){
            System.out.println(existingRun);
            System.out.println(existingRun.get());

            runs.set(runs.indexOf(existingRun.get()),run);
        }
    }

    void delete(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }
}
