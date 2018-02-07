--创建insert插入类型触发器
if (object_id('TGR_RecordBuyOrder_Insert', 'TR') is not null)
    drop trigger TGR_RecordBuyOrder_Insert
go
create trigger TGR_RecordBuyOrder_Insert
on RecordBuyOder
    for insert --插入触发
as
    --定义变量
    declare
		@UserID int, --用户ID
		@OderID nvarchar(128), --订单ID
		@CostMoney int,--订单金额
		@OrderTime datetime,--订单时间
		@AgencyId int,--直属代理ID
		@AgencyType int,--直属代理类型
		@AgencyParentId int,--上级代理ID
		@AgencyParentType int,--上级代理类型
		@AgencySFId int,--用于查找上级超级代理ID的第三变量
		@AgencySuperId int,--用于查找上级超级代理ID
		@AgencySuperType int,--用于查找上级超级代理类型
		@SuperAgentOneLevel varchar(255), --超级代理一级提成
		@SuperAgentTwoLevel varchar(255), --超级代理二级提成
		@SuperAgentOtherLevel varchar(255),--超级代理其他提成
		@CommonAgenOneLevel varchar(255),--普通代理一级提成
		@CommonAgenTwoLevel varchar(255);

	print 'This is start'
    --查询插入到订单表的数据
    select @UserID = UserID , @OderID = RecordID ,@CostMoney = CostMoney,@OrderTime = OderDate from inserted;
	--查询直属代理ID
	select @AgencyId = SpreaderID from QPAccountsDB.[dbo].[AccountsInfo] where UserID = @UserID;
	--查询直属代理类型
	select @AgencyType = agency_type,@AgencyParentId = parent_id  from AdminManager.[dbo].[agency] where id = @AgencyId;

	--获取各级代理提成额度
	select @SuperAgentOneLevel = param_value
		from AdminManager.[dbo].[game_config] where param_name = 'SuperAgentOneLevel';
	select @SuperAgentTwoLevel = param_value
		from AdminManager.[dbo].[game_config] where param_name = 'SuperAgentTwoLevel';
	select @SuperAgentOtherLevel = param_value
		from AdminManager.[dbo].[game_config] where param_name = 'SuperAgentOtherLevel';
	select @CommonAgenOneLevel = param_value
		from AdminManager.[dbo].[game_config] where param_name = 'CommonAgenOneLevel';
	select @CommonAgenTwoLevel = param_value
		from AdminManager.[dbo].[game_config] where param_name = 'CommonAgenTwoLevel';

	print 'start if 1'
	print @AgencyType
	print 'money'
	print @CostMoney
	if(@AgencyType = 2)
	begin
		print 'This is level1 type2';
		print 'param'
		print (@SuperAgentOneLevel/100)
		--超级代理一级提成
		INSERT INTO AdminManager.[dbo].[performance]
           ([agency_id]
           ,[level]
           ,[money]
           ,[cash_back_datetime]
           ,[record_buy_order_id])
		VALUES(
           @AgencyId
           ,1
           ,cast((round((@CostMoney * 100 * (cast(@SuperAgentOneLevel as decimal)/100)),0)) as int)
           ,@OrderTime
           ,@OderID
		   );
		UPDATE  AdminManager.[dbo].[agency] set agency_balance = agency_balance + cast((round((@CostMoney * 100 * (cast(@SuperAgentOneLevel as decimal)/100)),0)) as int) where id = @AgencyId
	end
	else if(@AgencyType = 3)
	begin
		print 'This is level1 type3';
		print 'param'
		print (@CommonAgenOneLevel/100)
		--普通代理一级提成
		INSERT INTO AdminManager.[dbo].[performance]
           ([agency_id]
           ,[level]
           ,[money]
           ,[cash_back_datetime]
           ,[record_buy_order_id])
		VALUES(
           @AgencyId
           ,1
           ,cast((round((@CostMoney * 100 * (cast(@CommonAgenOneLevel as decimal)/100)),0)) as int)
           ,@OrderTime
           ,@OderID
		   );
		UPDATE  AdminManager.[dbo].[agency] set agency_balance = agency_balance + cast((round((@CostMoney * 100 * (cast(@CommonAgenOneLevel as decimal)/100)),0)) as int) where id = @AgencyId
		--查询上级代理类型
		select @AgencyParentType = agency_type,@AgencySuperId = parent_id  from AdminManager.[dbo].[agency] where id = @AgencyParentId;
		print 'start if 2'
		if(@AgencyParentType = 2)
		begin
			print 'This is level2 type2';
			print 'param'
			print (@SuperAgentTwoLevel/100)
			--超级代理二级提成
			INSERT INTO AdminManager.[dbo].[performance]
			   ([agency_id]
			   ,[level]
			   ,[money]
			   ,[cash_back_datetime]
			   ,[record_buy_order_id])
			VALUES(
			   @AgencyParentId
			   ,2
			   ,cast((round((@CostMoney * 100 * (cast(@SuperAgentTwoLevel as decimal)/100)),0)) as int)
			   ,@OrderTime
			   ,@OderID
			   );
			 UPDATE  AdminManager.[dbo].[agency] set agency_balance = agency_balance + cast((round((@CostMoney * 100 * (cast(@SuperAgentTwoLevel as decimal)/100)),0)) as int) where id = @AgencyParentId
		end
		else if(@AgencyParentType = 3)
		begin
			print 'This is level2 type3';
			print 'param'
			print (@CommonAgenTwoLevel/100)
			--普通代理二级提成
			INSERT INTO AdminManager.[dbo].[performance]
			   ([agency_id]
			   ,[level]
			   ,[money]
			   ,[cash_back_datetime]
			   ,[record_buy_order_id])
			VALUES(
			   @AgencyParentId
			   ,2
			   ,cast((round((@CostMoney * 100 * (cast(@CommonAgenTwoLevel as decimal)/100)),0)) as int)
			   ,@OrderTime
			   ,@OderID
			   );
			UPDATE  AdminManager.[dbo].[agency] set agency_balance = agency_balance + cast((round((@CostMoney * 100 * (cast(@CommonAgenTwoLevel as decimal)/100)),0)) as int) where id = @AgencyParentId
			--循环查找到上级的超级代理为止
			set @AgencySuperId = @AgencyParentId ;
			set @AgencySuperType = @AgencyParentType;
			while @AgencyParentType <> 2
			begin
				set @AgencySFId = @AgencySuperId;
				select @AgencySuperType = agency_type,@AgencySuperId = parent_id  from AdminManager.[dbo].[agency] where id = @AgencySuperId;
			end
			print 'This is levelN typeN';
			print 'param'
			print (@SuperAgentOtherLevel/100)
			--超级代理其他提成
			INSERT INTO AdminManager.[dbo].[performance]
			   ([agency_id]
			   ,[level]
			   ,[money]
			   ,[cash_back_datetime]
			   ,[record_buy_order_id])
			VALUES(
			   @AgencySFId
			   ,2
			   ,cast((round((@CostMoney * 100 * (cast(@SuperAgentOtherLevel as decimal)/100)),0)) as int)
			   ,@OrderTime
			   ,@OderID
			   );
			UPDATE  AdminManager.[dbo].[agency] set agency_balance = agency_balance + cast((round((@CostMoney * 100 * (cast(@SuperAgentOtherLevel as decimal)/100)),0)) as int) where id = @AgencySFId
		end
	end
go