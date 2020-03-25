package com.example.act.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试类：
 * 测试所需要的activiti的25张表的生成
 */
public class ActivitiTest {
    @Test
    public void testGenTable() {
        //1.创建ProcessEngineConfiguration对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //2.创建ProcessEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println(processEngine);
    }


    public ProcessEngine getProcessEngine(){
        //获取流程引擎

        ProcessEngine processEngine = ProcessEngineConfiguration.

        createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();

        return processEngine;
    }

    public Deployment processdefinition(ProcessEngine processEngine,Map<String,String> parameterMap){
        //获取流程引擎
        //ProcessEngine processEngine=getProcessEngine();
        //获取部署名称
        String deploymentName=parameterMap.get("deploymentName");
       //bpmn资源文件路径
        String bpmnPath=parameterMap.get("bpmnPath");
       //png资源文件路径
        String pngPath=parameterMap.get("pngPath");

        /**

          * 1.流程定义

          * 这里使用RepositoryService部署流程定义

          * addClasspathResource表示从类路径下加载资源文件，一次只能加载一个文件

          */

        //获取服务实例

        Deployment deployment=processEngine.getRepositoryService()// 与流程定义和部署对象相关的service

                .createDeployment()// 创建一个部署对象

//              .name("流程定义")// 添加部署的名称
                .name(deploymentName)

//                .addClasspathResource("bpmn/test.bpmn")// 从classpath的资源中加载，一次只能加载一个文件

//                .addClasspathResource("bpmn/test.png")// 从classpath的资源中加载，一次只能加载一个文件

                .addClasspathResource(bpmnPath)
                .addClasspathResource(pngPath)
                .deploy();// 完成部署

        System.out.println("部署ID:"+ deployment.getId());

        System.out.println("部署名称:"+ deployment.getName());

        System.out.println("****************************");

        return deployment;
    }

    public void startupProcess(ProcessEngine processEngine){
        /**

         * 2.启动流程

         * 这里使用RuntimeService启动流程实例

         */

        //使用流程定义的key即流程图MyProcess的id启动流程实例

        String processDefinitionKey="1";

        // 创建流程变量

        Map<String,Object> variables= new HashMap<String,Object>();

        variables.put("applyname", "wangwu");

        // 在启动时设置流程变量

        // 使用指定key的最新版本的流程定义启动流程实例，并设置一些流程变量

        //ProcessInstance pi =processEngine.getRuntimeService().startProcessInstanceByKey("myProcess");

        ProcessInstance pi=processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey, variables);

        System.out.println("流程实例ID:"+pi.getId());

        System.out.println("流程定义ID:"+ pi.getProcessDefinitionId());

        System.out.println("****************************");
    }
    @Test
    public void Process() throws Exception {




    }
}
