package com.profiling.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapLeaker {
	
    public ExecutorService exec = Executors.newFixedThreadPool(40);
    public Map<Task, TaskStatus> taskStatus = Collections.synchronizedMap(new HashMap<Task, TaskStatus>());
    private Random random = new Random();
 
    private enum TaskStatus { NOT_STARTED, STARTED, FINISHED };
 
    private class Task implements Runnable {
        private int[] numbers = new int[random.nextInt(200)];
 
        public void run() {
            int[] temp = new int[random.nextInt(10000)];
            taskStatus.put(this, TaskStatus.STARTED);
            doSomeWork();
            taskStatus.put(this, TaskStatus.FINISHED);
        }
    }
 
    public Task newTask() {
        Task t = new Task();
        taskStatus.put(t, TaskStatus.NOT_STARTED);
        exec.execute(t);
        return t;
    }
    
    public void createTasks() {
    	
    	for(int v = 1; v <= 40; v++) {
    		newTask();
    	}
    }
    
    public void doSomeWork() {
    	
    	for(int i = 0;i <= 20; i++) {
    		System.out.println(this.getClass() + " estou processando varias vezes [" + i + "]");
    	}
    	
    }
}