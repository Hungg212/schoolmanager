USE [school123456]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[students](
	[id] [int] NOT NULL,
	[name] [nvarchar](100) NULL,
	[email] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[students] ([id], [name], [email]) VALUES (1, N'Nguyễn Văn A', N'a@gmail.com')
INSERT [dbo].[students] ([id], [name], [email]) VALUES (2, N'Trần Thị B', N'b@gmail.com')
INSERT [dbo].[students] ([id], [name], [email]) VALUES (3, N'Lê Văn C', N'c@gmail.com')