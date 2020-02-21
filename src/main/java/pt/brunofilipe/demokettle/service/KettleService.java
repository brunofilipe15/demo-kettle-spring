package pt.brunofilipe.demokettle.service;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class KettleService {

    @Autowired private TaskExecutor taskExecutor;

    public void executeAsynchronouslyJobKettle() {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // From - https://anotherreeshu.wordpress.com/2015/01/18/java-and-pentaho-kettle-executing-a-kettle-file-using-java/
                String file = "src/main/resources/kettle/jobHello.kjb"; //Job file path that needs to be executed
                Repository repository = null; //Checking for repository

                System.out.println("-> Start");

                try {
                    KettleEnvironment.init();

                    JobMeta jobmeta = new JobMeta(file, repository);
                    Job job = new Job(repository, jobmeta);

                    job.start();
                    job.waitUntilFinished();

                    if (job.getErrors() > 0) {
                        System.out.println("Error Executing Job");
                    }

                } catch (KettleException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("-> Finish");
            }
        });
    }
}