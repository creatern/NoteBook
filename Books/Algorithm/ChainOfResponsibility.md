- 职责链模式（Chain of Responsibility）：请求的每个接收者都包含对下一个接收者的引用，如果当前接收者不能处理该请求，则将相同的请求传递给下一个接收者，直到该请求被处理。

1. 使用者不需要知道链的结构，职责链减少了其本身与处理逻辑之间的耦合，规范处理过程。
2. 设置阈值控制链的最大节点数量，减少对性能的影响。
3. 将请求和处理分离，解耦。

> JVM类加载器Classloader的双亲委派机制

<img title="" src="../../pictures/设计模式-ChainOfResponsibility.drawio.svg" alt="" width="472">

<img src="../../pictures/设计模式-ChainOfResponsibility-School.drawio.svg" width="600"/>

```java
PurchaseRequest request = new PurchaseRequest();

DepartmentApprover department = new DepartmentApprover();
CollegeApprover collegue = new CollegueApprover();
SchoolMasterApprover schoolMaster = new SchoolMasterApprover();
department.setNextApprover(collegue);
collegue.setNextApprover(schoolMaster);

department.processRequest(request);
```

```java
abstract class Approver{

    protected approver Approver;

    public void setNextApprover(Approver approver){
    this.approver = approver;
    }

    public abstract void processRequest(PurchaseRequest request);

}


class DepartmentApprover extends Approver{

    public void processRequest(PurchaseRequest request){
        if(request.num < 1000) System.out.println("处理完毕");
        else approver.processRequest(request);
    }

}

class PurchaseRequest{

    public int num;

}
```
