--创建update插入类型触发器
if (object_id('TGR_Game_Update', 'TR') is not null)
    drop trigger TGR_Game_Update
go
create trigger TGR_Game_Update
on game_config
    for update --插入触发
as
     --定义变量
    declare
		@title varchar(255), --标题
		@content varchar(255), --内容
		@desc varchar(255), --描述
		@oldValue varchar(255)

	select @title = param_name , @content = param_value ,@desc = param_desc from inserted;
	select @oldValue = StatusValue from QPAccountsDB.[dbo].[SystemStatusInfo] where StatusName = @title
	if(@oldValue is not null)
	begin
		update QPAccountsDB.[dbo].[SystemStatusInfo] set StatusString = @content, StatusValue = @content,StatusTip = @desc where StatusName = @title
	end
go