<table>
    <thead>
        <tr>
            <th width="15%">Initializr结构</th>
            <th width="85%">说明</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                <a href="./SpringBootApplication.md">@SpringBootApplication</a>
            </td>
            <td>SpringBoot启动类</td>
        </tr>
        <tr>
            <td>/static</td>
            <td>静态资源</td>
        </tr>
        <tr>
            <td>/templates</td>
            <td>模板文件</td>
        </tr>
        <tr>
            <td>application.properties
                <br />application.yml
            </td>
            <td>配置文件</td>
        </tr>
    </tbody>
</table>

- Starter依赖管理（<a href="./spring-boot-starter">spring\-boot\-starter</a>）：Spring对依赖包的集中描述，本身不包含库代码，而是传递性地拉取其他库。
- [Spring Boot DevTools](./DevTools.md)：Spring开发环境工具，应用部署后DevTools禁用自身。
- [配置属性](./配置属性.md)：Spring从各个属性源获取数据并注入到各个Bean。