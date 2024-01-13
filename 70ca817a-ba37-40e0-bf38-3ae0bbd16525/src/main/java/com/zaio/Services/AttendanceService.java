package com.zaio.Services;

import com.zaio.Entity.Attendance;
import com.zaio.Repositories.AttendanceRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    /* Create method to Save the Attendance object in the database. */
}
