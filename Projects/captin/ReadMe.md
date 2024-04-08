# Captin

<table>
    <tr>
        <td width="15%">captin-boot</td>
        <td width="85%">Spring Boot</td>
    </tr>
    <tr>
        <td>captin-vue</td>
        <td>Vue</td>
    </tr>
</table>
<img src="./assets/captin-技术选型.drawio.svg" width="600"/> 

## captin-boot

## captin-vue

<img src="./assets/captin-网页结构.drawio.svg" width="600"/> 

## MySQL

<img src="./assets/captin-E-R.drawio.svg" width="100%"/> 

# 功能解释

## MySQL数据中的数据来源

1. clips：通过脚本对clip目录进行扫描。其中，clip目录的内部结构为“repositries(`order)name`) / documents(`order)title`) / clips(`order)title`)”。通过这种扫描方式可以生成大多数需要的数据。
2. clips组成documents，documents组成repositories；且是多对多的关系。clips是最小的原子性的。
3. `_order`：分别为仓库、文档、片段的顺序，用于目录的生成。
4. `_type`：片段类型，0-表示为普通的片段，1-表示为文档的简介，2-表示为仓库的简介。
5. `_level`：片段级别，`1~4` 依次对应片段的标题在文档中

