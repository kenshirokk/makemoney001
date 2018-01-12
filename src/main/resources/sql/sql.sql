-- 修改公告表 2个字段的长度为max
ALTER TABLE AdminManager.dbo.bulletin ALTER COLUMN content VARCHAR(MAX);
ALTER TABLE AdminManager.dbo.bulletin ALTER COLUMN [desc] VARCHAR(MAX);

-- 删除 salary_balance 字段
ALTER TABLE AdminManager.dbo.agency DROP COLUMN salary_balance;
