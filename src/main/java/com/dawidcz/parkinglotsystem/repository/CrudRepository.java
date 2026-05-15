package com.dawidcz.parkinglotsystem.repository;

import java.sql.Connection;

abstract class CrudRepository<T> {
    protected Connection connection;

    public CrudRepository(Connection connection){
        this.connection = connection;

    }



}
