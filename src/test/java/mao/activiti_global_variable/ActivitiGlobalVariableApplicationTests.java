package mao.activiti_global_variable;

import mao.activiti_global_variable.entity.Evection;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ActivitiGlobalVariableApplicationTests
{
    private static final Logger log = LoggerFactory.getLogger(ActivitiGlobalVariableApplicationTests.class);

    @Test
    void contextLoads()
    {

    }

    @Test
    void createDeployment()
    {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .name("出差定义")
                .addClasspathResource("test.bpmn20.xml")
                .deploy();
        log.info("流程id：" + deployment.getId());
        log.info("流程名称：" + deployment.getName());
    }

    @Test
    void startProcess()
    {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //变量集合
        Map<String, Object> map = new HashMap<>();
        //创建出差实体类
        Evection evection = new Evection();
        evection.setNum(2d);
        //实体类
        map.put("evection", evection);
        //人名参数
        map.put("assignee0", "张三");
        map.put("assignee1", "李经理");
        map.put("assignee2", "王总经理");
        map.put("assignee3", "赵财务");
        //启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test", map);
        log.info("id:" + processInstance.getId());
        log.info("名称：" + processInstance.getName());
    }


    /**
     * 张三提交任务
     */
    @Test
    void completeTask1()
    {
        String assingee = "张三";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("test")
                .taskAssignee(assingee)
                .orderByTaskCreateTime()
                .desc()
                .list()
                .get(0);
        if (task == null)
        {
            log.info("无权操作");
        }
        else
        {
            taskService.complete(task.getId());
            log.info("任务执行完成");
        }
    }

    /**
     * 张三提交任务，任务办理时设置变量
     */
    @Test
    void completeTask1_1()
    {
        String assingee = "张三";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("test")
                .taskAssignee(assingee)
                .orderByTaskCreateTime()
                .desc()
                .list()
                .get(0);
        if (task == null)
        {
            log.info("无权操作");
        }
        else
        {
            Map<String, Object> map = new HashMap<>();
            //创建出差实体类
            Evection evection = new Evection();
            evection.setNum(2d);
            //实体类
            map.put("evection", evection);
            taskService.complete(task.getId(), map);
            log.info("任务执行完成");
        }
    }

    /**
     * 通过当前流程实例设置
     */
    @Test
    void setGlobalVariableByExecutionId()
    {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Evection evection = new Evection();
        evection.setNum(3d);
        runtimeService.setVariable("7501", "evection", evection);
    }

    @Test
    void setGlobalVariableByTaskId()
    {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Evection evection = new Evection();
        evection.setNum(3d);
        taskService.setVariable("2501", "evection", evection);
    }
}
