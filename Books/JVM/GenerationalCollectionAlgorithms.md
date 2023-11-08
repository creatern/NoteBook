- 分代收集算法：不同生命周期的对象采取不同的收集方式。
- 年轻代（Young Gen）区域较小、对象生命周期短、存活率低、回收频繁。
  - 复制算法：survivor1、survivor2。
- 老年代（Tenured Gen）区域较大、对象生命周期长、存活率高、回收不如年轻代频繁。
  - 标记-清除、标记-整理的混合实现：
    - Mark：开销和存活对象数量成正比。
    - Sweep：开销和所管理区域大小成正比。
    - Compact：开销和存活对象数量成正比。

> Hotspot的CMS回收器：CMS基于Mark-Sweep实现，对象的回收率高。
>
> - 碎片问题：CMS采用基于Mark-Compact算法的Serial Old回收器作为补偿。当内存回收不佳时（碎片导致的Concurrent Mode Failure），采用Serial Old执行Full GC以达到对老年代内存的整理。