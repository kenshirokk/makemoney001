--创建update插入类型触发器
if (object_id('TGR_Bulletin_Update', 'TR') is not null)
    drop trigger TGR_Bulletin_Update
go
create trigger TGR_Bulletin_Update
on bulletin
    for update --插入触发
as
    --定义变量
    declare
		@title varchar(255), --标题
		@content varchar(255), --内容
		@desc varchar(255); --描述
		
	select @title = title , @content = content ,@desc = [desc] from inserted;
	update QPAccountsDB.[dbo].[SystemStatusInfo] set StatusString = @content,StatusTip = @desc where StatusName = @title
go