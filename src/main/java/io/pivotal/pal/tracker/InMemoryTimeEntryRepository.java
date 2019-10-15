package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> data = new HashMap<>();

    private long count = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = new TimeEntry(count, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        count++;
        data.put(createdTimeEntry.getId(), createdTimeEntry);
        return createdTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return data.get(timeEntryId);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(data.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (data.get(id) != null) {
            TimeEntry updatedTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
            data.replace(id, updatedTimeEntry);
            return updatedTimeEntry;
        }
        return null;
    }

    @Override
    public void delete(long timeEntryId) {
        data.remove(timeEntryId);
    }
}
