package com.ca227.C1221342;


import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StaffService {
    private Map<Long , Staff> staffs=new ConcurrentHashMap<>();
    private AtomicLong autoid=new AtomicLong();

    public  Collection<Staff> getAllStaffs(){
        return staffs.values();
    }

    public Staff getStaffById(Long id){
        return staffs.get(id);
    }

    public Staff createStaff(Staff NewStaff){
        Long staffid = NewStaff.getId()!=null
                ? NewStaff.getId()
                :autoid.incrementAndGet();
        NewStaff.setId(staffid);
        staffs.put(staffid,NewStaff);
        return  NewStaff;
    }


    public Staff UpdateStaff(Long id, Staff newStaff) {
        if (staffs.containsKey(id)) {
            Staff oldStaff = getStaffById(id);
            oldStaff.setName(newStaff.getName());
            oldStaff.setRole(newStaff.getRole());

            staffs.put(id, oldStaff);
            return oldStaff;
        } else {
            return null;
        }
    }


    public void deleteStaff(Long id){
        staffs.remove(id);
    }

}