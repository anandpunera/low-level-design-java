package com.lld.vr.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Slot {
    private Date start;
    private Date end;

    public boolean overlaps(Slot slot) {
        return (this.start.after(slot.start) && this.end.after(slot.end)) || (this.start.before(slot.start)&&this.end.before(slot.end));
    }
}
