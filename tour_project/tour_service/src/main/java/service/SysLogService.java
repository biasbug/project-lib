package service;

import domain.SysLog;

import java.util.List;

public interface SysLogService {
    public void save(SysLog sysLog);
    public List<SysLog> findAll();
}
