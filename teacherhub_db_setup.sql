USE [master]
GO
/****** Object:  Database [TeacherHubDB]    Script Date: 21/02/2025 18:06:35 ******/
CREATE DATABASE [TeacherHubDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TeacherHubDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\TeacherHubDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'TeacherHubDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\TeacherHubDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [TeacherHubDB] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TeacherHubDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TeacherHubDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TeacherHubDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TeacherHubDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TeacherHubDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TeacherHubDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [TeacherHubDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TeacherHubDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TeacherHubDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TeacherHubDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TeacherHubDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TeacherHubDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TeacherHubDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TeacherHubDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TeacherHubDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TeacherHubDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [TeacherHubDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TeacherHubDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TeacherHubDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TeacherHubDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TeacherHubDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TeacherHubDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TeacherHubDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TeacherHubDB] SET RECOVERY FULL 
GO
ALTER DATABASE [TeacherHubDB] SET  MULTI_USER 
GO
ALTER DATABASE [TeacherHubDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TeacherHubDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TeacherHubDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TeacherHubDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [TeacherHubDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [TeacherHubDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'TeacherHubDB', N'ON'
GO
ALTER DATABASE [TeacherHubDB] SET QUERY_STORE = ON
GO
ALTER DATABASE [TeacherHubDB] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [TeacherHubDB]
GO
/****** Object:  User [cvoicu]    Script Date: 21/02/2025 18:06:36 ******/
CREATE USER [cvoicu] FOR LOGIN [cvoicu] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [cvoicu]
GO
ALTER ROLE [db_datareader] ADD MEMBER [cvoicu]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [cvoicu]
GO
/****** Object:  Table [dbo].[Enrollment]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Enrollment](
	[EnrollmentID] [int] IDENTITY(1,1) NOT NULL,
	[StudentID] [int] NOT NULL,
	[SubjectID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[EnrollmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Grades]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Grades](
	[GradeID] [int] IDENTITY(1,1) NOT NULL,
	[StudentID] [int] NOT NULL,
	[SubjectID] [int] NOT NULL,
	[Grade] [decimal](4, 2) NULL,
	[DateAssigned] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[GradeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subjects]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subjects](
	[SubjectID] [int] IDENTITY(1,1) NOT NULL,
	[SubjectName] [nvarchar](100) NOT NULL,
	[TeacherID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SubjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [nvarchar](100) NOT NULL,
	[LastName] [nvarchar](100) NOT NULL,
	[UserTypeID] [int] NOT NULL,
	[Password] [nvarchar](255) NOT NULL,
	[Email] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserType]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserType](
	[TypeID] [int] IDENTITY(1,1) NOT NULL,
	[TypeName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD FOREIGN KEY([StudentID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD FOREIGN KEY([SubjectID])
REFERENCES [dbo].[Subjects] ([SubjectID])
GO
ALTER TABLE [dbo].[Grades]  WITH CHECK ADD FOREIGN KEY([StudentID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[Grades]  WITH CHECK ADD FOREIGN KEY([SubjectID])
REFERENCES [dbo].[Subjects] ([SubjectID])
GO
ALTER TABLE [dbo].[Subjects]  WITH CHECK ADD FOREIGN KEY([TeacherID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD FOREIGN KEY([UserTypeID])
REFERENCES [dbo].[UserType] ([TypeID])
GO
ALTER TABLE [dbo].[Grades]  WITH CHECK ADD CHECK  (([Grade]>=(1.00) AND [Grade]<=(10.00)))
GO
/****** Object:  StoredProcedure [dbo].[AddGradeForStudent]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AddGradeForStudent]
    @StudentID INT,
    @SubjectID INT,
    @Grade DECIMAL(4, 2)
AS
BEGIN
    -- Validate if the Student exists
    IF NOT EXISTS (SELECT 1 FROM Users WHERE UserID = @StudentID)
    BEGIN
        PRINT 'Error: Invalid StudentID.';
        RETURN;
    END

    -- Validate if the Subject exists
    IF NOT EXISTS (SELECT 1 FROM Subjects WHERE SubjectID = @SubjectID)
    BEGIN
        PRINT 'Error: Invalid SubjectID.';
        RETURN;
    END

    -- Insert the grade into the Grades table
    INSERT INTO Grades (StudentID, SubjectID, Grade, DateAssigned)
    VALUES (@StudentID, @SubjectID, @Grade,  GETDATE());

    PRINT 'Grade added successfully.';
END;
GO
/****** Object:  StoredProcedure [dbo].[DeleteGradeForStudent]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[DeleteGradeForStudent]
    @GradeID INT
AS
BEGIN
    -- Check if the Grade exists
    IF NOT EXISTS (SELECT 1 FROM Grades WHERE GradeID = @GradeID)
    BEGIN
        PRINT 'Error: GradeID does not exist.';
        RETURN;
    END

    -- Delete the grade
    DELETE FROM Grades
    WHERE GradeID = @GradeID;

    PRINT 'Grade deleted successfully.';
END;
GO
/****** Object:  StoredProcedure [dbo].[GetAllStudents]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetAllStudents]
AS
BEGIN
    SELECT 
        UserID,
        FirstName,
        LastName,
		Email,
		Password
    FROM 
        Users
    WHERE 
        UserTypeID = (SELECT TypeID FROM UserType WHERE TypeName = 'Student');
END;
GO
/****** Object:  StoredProcedure [dbo].[GetAllSubjects]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetAllSubjects]
AS
BEGIN
    SELECT 
        s.SubjectID,
        s.SubjectName,
        u.UserID AS TeacherID,
        CONCAT(u.FirstName, ' ', u.LastName) AS TeacherName
    FROM 
        Subjects s
    INNER JOIN Users u ON s.TeacherID = u.UserID;
END;
GO
/****** Object:  StoredProcedure [dbo].[GetAllTeachers]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetAllTeachers]
AS
BEGIN
    SELECT 
        UserID,
        FirstName,
        LastName,
		Email,
		Password
    FROM 
        Users
    WHERE 
        UserTypeID = (SELECT TypeID FROM UserType WHERE TypeName = 'Teacher');
END;
GO
/****** Object:  StoredProcedure [dbo].[GetGradesForSubject]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetGradesForSubject]
    @UserID INT,       -- Student's UserID
    @SubjectID INT     -- SubjectID
AS
BEGIN
    SELECT 
        g.GradeID,
        g.Grade,
        g.DateAssigned,
        g.StudentID,
		g.SubjectID,
        CONCAT(u.LastName, ' ', u.FirstName) AS StudentName
    FROM 
        Grades g
    INNER JOIN Users u ON g.StudentID = u.UserID
    WHERE 
        g.SubjectID = @SubjectID
    AND g.StudentID = @UserID  -- Filter by both Student (UserID) and Subject
    AND u.UserTypeID = 2;      -- Ensure it's a student (UserTypeID = 2)
END;
GO
/****** Object:  StoredProcedure [dbo].[GetGradesForSubjectAllStudents]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[GetGradesForSubjectAllStudents]
    @SubjectID INT     -- SubjectID
AS
BEGIN
    SELECT 
        g.GradeID,
        g.Grade,
        g.DateAssigned,
        g.StudentID,
		g.SubjectID,
        CONCAT(u.LastName, ' ', u.FirstName) AS StudentName
    FROM 
        Grades g
    INNER JOIN Users u ON g.StudentID = u.UserID
    WHERE 
        g.SubjectID = @SubjectID
    AND u.UserTypeID = 2;      -- Ensure it's a student (UserTypeID = 2)
END;
GO
/****** Object:  StoredProcedure [dbo].[GetStudentsForSubject]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetStudentsForSubject]
    @SubjectID INT
AS
BEGIN
    SELECT 
        e.StudentID,
        u.FirstName,
		u.LastName
    FROM 
        Enrollment e
    INNER JOIN Users u ON e.StudentID = u.UserID
    WHERE 
        e.SubjectID = @SubjectID;
END;
GO
/****** Object:  StoredProcedure [dbo].[GetSubjectsByStudent]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetSubjectsByStudent]
    @StudentID INT
AS
BEGIN
    SELECT s.SubjectID, s.SubjectName
    FROM Subjects s
    JOIN Enrollment e ON s.SubjectID = e.SubjectID
    WHERE e.StudentID = @StudentID;
END;
GO
/****** Object:  StoredProcedure [dbo].[GetUserTypeByEmail]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetUserTypeByEmail]
    @Email NVARCHAR(255)
AS
BEGIN
    SELECT 
        u.UserID,
        u.FirstName,
        u.LastName,
        u.Email,
		u.Password,
        ut.TypeName AS UserType
    FROM 
        Users u
    INNER JOIN 
        UserType ut ON u.UserTypeID = ut.TypeID
    WHERE 
        u.Email = @Email;
END;
GO
/****** Object:  StoredProcedure [dbo].[UpdateGradeForStudent]    Script Date: 21/02/2025 18:06:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[UpdateGradeForStudent]
    @GradeID INT,
    @NewGrade DECIMAL(4, 2),
    @NewDateAssigned DATE
AS
BEGIN
    -- Check if the Grade exists
    IF NOT EXISTS (SELECT 1 FROM Grades WHERE GradeID = @GradeID)
    BEGIN
        PRINT 'Error: GradeID does not exist.';
        RETURN;
    END

    -- Update the grade and date
    UPDATE Grades
    SET Grade = @NewGrade,
        DateAssigned = @NewDateAssigned
    WHERE GradeID = @GradeID;

    PRINT 'Grade updated successfully.';
END;
GO
USE [master]
GO
ALTER DATABASE [TeacherHubDB] SET  READ_WRITE 
GO
