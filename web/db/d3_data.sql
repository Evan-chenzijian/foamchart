CREATE TABLE [force_data] 
(
    [id] int IDENTITY(1,1) NOT NULL,
    [source] varchar(255) NULL,
    [target] varchar(255) NULL,
    [type] varchar(255) NULL,
    PRIMARY KEY ([id]) 
)

exec sp_addextendedproperty N'MS_Description', N'箭头起点', N'user', N'dbo', N'table', N'force_data', N'column', N'source';
exec sp_addextendedproperty N'MS_Description', N'箭头终点', N'user', N'dbo', N'table', N'force_data', N'column', N'target';
exec sp_addextendedproperty N'MS_Description', N'连线类型', N'user', N'dbo', N'table', N'force_data', N'column', N'type';
exec sp_addextendedproperty N'MS_Description', N'', N'user', N'dbo', N'table', N'force_data';

CREATE TABLE [link_data] 
(
    [id] int IDENTITY(1,1) NOT NULL,
    [prename] varchar(255) NULL,
    [newcode] varchar(255) NULL,
    PRIMARY KEY ([id]) 
)

exec sp_addextendedproperty N'MS_Description', N'连接点名称', N'user', N'dbo', N'table', N'link_data', N'column', N'prename';
exec sp_addextendedproperty N'MS_Description', N'连接目标文件名', N'user', N'dbo', N'table', N'link_data', N'column', N'newcode';
exec sp_addextendedproperty N'MS_Description', N'', N'user', N'dbo', N'table', N'link_data';

CREATE TABLE [main_data] 
(
    [idmain_data] int IDENTITY(1,1) NOT NULL,
    [label] varchar(255) NULL,
    [seq] int NOT NULL,
    PRIMARY KEY ([idmain_data]) 
)

exec sp_addextendedproperty N'MS_Description', N'主流程名称', N'user', N'dbo', N'table', N'main_data', N'column', N'label';
exec sp_addextendedproperty N'MS_Description', N'排列顺序', N'user', N'dbo', N'table', N'main_data', N'column', N'seq';
exec sp_addextendedproperty N'MS_Description', N'', N'user', N'dbo', N'table', N'main_data';

CREATE TABLE [tree_data] 
(
    [id] int IDENTITY(1,1) NOT NULL,
    [firstLv] varchar(255) NULL,
    [lastLv] varchar(255) NULL,
    [secondLv] varchar(255) NULL,
    [thirdLv] varchar(255) NULL,
    [type] varchar(255) NULL,
    PRIMARY KEY ([id]) 
)

exec sp_addextendedproperty N'MS_Description', N'第一层点', N'user', N'dbo', N'table', N'tree_data', N'column', N'firstLv';
exec sp_addextendedproperty N'MS_Description', N'第四层点', N'user', N'dbo', N'table', N'tree_data', N'column', N'lastLv';
exec sp_addextendedproperty N'MS_Description', N'第二层点', N'user', N'dbo', N'table', N'tree_data', N'column', N'secondLv';
exec sp_addextendedproperty N'MS_Description', N'第三层点', N'user', N'dbo', N'table', N'tree_data', N'column', N'thirdLv';
exec sp_addextendedproperty N'MS_Description', N'所属系统', N'user', N'dbo', N'table', N'tree_data', N'column', N'type';
exec sp_addextendedproperty N'MS_Description', N'', N'user', N'dbo', N'table', N'tree_data';

