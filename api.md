目标：
建立一个专注于用户登陆验证、权限管理以及加密的微服务
部分关键信息：
1、数据表：用户表（账号、密码）、用户详细信息表（手机号、微信、出生日期、性别等）、用户VIP等级表、权限表
功能：
1、用户注册验证用户登陆信息（首次登陆均为大众会员）
2、注册成功将信息加密存储到数据库
3、用户详细信息填写存储功能，将信息存储到数据库
4、用户开通VIP界面，将用户开通的VIP以及对应的权限绑定



表的映射关系

映射单向 n-1 的关联关系：

​使用 @ManyToOne 来映射多对一的关联关系
​使用 @JoinColumn 来映射外键
可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
​FetchType.LAZY：懒加载，加载一个实体时，定义懒加载的属性不会马上从数据库中加载
FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载
save
保存多对一时, 建议先保存 1 的一端, 后保存 n 的一端, 这样不会多出额外的 UPDATE 语句.
先保存n方，会产生3条insert语句，2条upate语句
find
(1)默认情况下, 使用【左外连接，即左连接,left outer join】的方式来获取 n 的一端的对象和其关联的 1 的一端的对象
(2)懒加载：可使用 @ManyToOne 的 fetch(fetch=FetchType.LAZY) 属性来修改默认的关联属性的加载策略

映射单向 1-n 的关联关系:

​使用 @OneToMany 来映射 1-n 的关联关系
使用 @JoinColumn 来映射外键列的名称
可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略【默认值为lazy】
可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则此端(@OneToMany)就不能再使用 @JoinColumn 属性了.
注意 CascadeType的取值
CascadeType.REMOVE
Cascade remove operation，级联删除操作。删除当前实体时，与它有映射关系的实体也会跟着被删除。
CascadeType.MERGE
Cascade merge operation，级联更新（合并）操作。当Student中的数据改变，会相应地更新Course中的数据。
CascadeType.DETACH
Cascade detach operation，级联脱管/游离操作。如果你要删除一个实体，但是它有外键无法删除，你就需要这个级联权限了。它会撤销所有相关的外键关联。
CascadeType.REFRESH
Cascade refresh operation，级联刷新操作。
假设场景 有一个订单,订单里面关联了许多商品,这个订单可以被很多人操作,那么这个时候A对此订单和关联的商品进行了修改,与此同时,B也进行了相同的操作,但是B先一步比A保存了数据,那么当A保存数据的时候,就需要先刷新订单信息及关联的商品信息后,再将订单及商品保存
CascadeType.ALL
Cascade all operations，清晰明确，拥有以上所有级联操作权限。
remove
默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行1的一端的删除操作【注意：n端只是删除了外键值，数据保留】
可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略，级联删除【删除两端所有相关】

一对多关系还可以存在中间表:

@JoinTable(name = “jpa06_middle_customer_order”,joinColumns = {@JoinColumn(name = “customer_id”) },inverseJoinColumns = { @JoinColumn(name = “order_id”) })
JoinTable 中间表的名字，joinColumns 这张表在中间表的id，inverseJoinColumns 多方在中间表中的名字
save
单向 1-n 关联关系执行保存时, 一定会多出 UPDATE 语句.因为 n 的一端在插入时不会同时插入外键列
find
​默认对关联的多的一方使用懒加载的加载策略.可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略，改为 fetch=FetchType.EAGER 【left outer join】
remove
默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行1的一端的删除操作【注意：n端只是删除了外键值，数据保留】
可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略，级联删除【删除两端所有相关】

双向一对多配置:

在一方配置onetomany
@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
在多方配置
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = “equipment_fid”, referencedColumnName = “fid”)
1.cascade = { CascadeType.REFRESH, CascadeType.MERGE }
级联刷新和级联保存，级联删除和级联更新会出错，需要自己手动保存和更新
2.optional = true，默认为true 级查询字段 结果可以默认为空
3. @JoinColumn(name = “order_id”) 定义数据库中的字段名，不写name=”“默认为属性名
4. @NotFound(action= NotFoundAction.IGNORE) 忽略找不到为空的报错
5. @JsonIgnoreProperties(“students”) 级联查询 查询订单的时候查询出订单详情，订单里的订单详情里不在查询订单。查询订单详情的时候查询出其订单，不在查询订单里的订单详情。
6. fetch 一查多 默认lazy 及不查询出来多，想查询出来，在想查询的接口上加上注解@EntityGraph(attributePaths = {“OrderItem”})

一对一配置:

使用 @OneToOne 来映射 1-1 关联关系
若需要在当前数据表中添加主键则需要使用 @JoinColumn 来进行映射. 注意, 1-1 关联关系需要添加 unique=true
对于不维护关联关系, 没有外键的一方, 使用 @OneToOne 来进行映射, 建议设置 mappedBy=true

多对多的配置:

使用 @ManyToMany 注解来映射多对多关联关系
​使用 @JoinTable 来映射中间表:
1. name 指向中间表的名字
2. joinColumns 映射当前类所在的表在中间表中的外键
2.1 name 指定外键列的列名
2.2 referencedColumnName 指定外键列关联当前表的哪一列
3. inverseJoinColumns 映射关联的类所在中间表的外键
//放弃维护关系
@ManyToMany(mappedBy = “categories”)
find

​ ① 对于关联的集合对象, 默认使用懒加载的策略.

​ ② 使用维护关联关系的一方获取, 还是使用不维护关联关系的一方获取, SQL语句相同

​原因：存在中间表，双方对称多对多配置的时候还可以自己设置中间表，但是在两个多方表中需要配置@OneToMany(mappedBy = “student”) ，在中间表中都需要配置：
@ManyToOne
@JoinColumn(name = “stu_id”)

注意 这个表关联的两个个字段都需要配置的。
