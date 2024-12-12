package com.backend.simutaltion;

/**
 * Define a condition for stopping the simulation
 * Used by vendor and customer threads to check if they should continue running
 */
@FunctionalInterface
public interface StopCondition {
    /**
     * Determine whether the simulation should stop
     *
     * @return true if simulation should stop
     * else false
     */
    boolean shouldStop();
}

