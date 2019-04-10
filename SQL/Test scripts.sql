use prague;

CREATE TABLE `hua_file_base64` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `original_file_name` varchar(145) DEFAULT NULL COMMENT 'The name of the file prior to conversion.',
  `base64_string` longtext COMMENT 'The base64 conversion string of the file.',
  `original_file_directory` varchar(5000) DEFAULT NULL COMMENT 'The Directory the file was in prior to conversion.',
  `date_converted` datetime DEFAULT NULL,
  `test` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='the base 64 conversions of the files given to britehouse';

select * from hua_file_base64;

select id, original_file_name, base64_string, original_file_directory, date_converted, (select user_id from user_details) as test from hua_file_base64;

-- without missing workday ID's
select h.id, h.original_file_name, h.base64_string, h.original_file_directory, h.date_converted, u.user_id, uw.Workday_Candidate_ID 
from hua_file_base64 h,
user_details u,
user_workday uw 
where h.original_file_directory = u.directory
and u.user_id = uw.Candidate_GUID
and h.id between 17854 and 19526;

select h.id, h.original_file_name, h.base64_string, h.original_file_directory, h.date_converted, u.user_id, uw.Workday_Candidate_ID 
from hua_file_base64 h,
user_details u,
user_workday uw 
where h.original_file_directory = u.directory
and u.user_id = uw.Candidate_GUID
and h.id between 19525 and 27000;

select h.id from hua_file_base64 h,
user_details u,
user_workday uw 
where h.original_file_directory = u.directory
and u.user_id = uw.Candidate_GUID;

-- show where Workday ID is found
select h.id, h.original_file_name, h.base64_string, h.original_file_directory, h.date_converted, u.user_id, uw.Workday_Candidate_ID 
from hua_file_base64 h,
user_details u
LEFT JOIN user_workday uw on u.user_id = uw.Candidate_GUID
where h.original_file_directory = u.directory;
-- and u.user_id = uw.Candidate_GUID;

select * from user_workday;
select * from user_details;
drop table hua_file_base64;

delete from hua_file_base64;

select original_file_directory 
from hua_file_base64
where original_file_directory like '%Prague Attachments\\\\Prague Attachments\\\\Prague Attachments%';

insert into user(string_id, directory) ( SELECT SUBSTRING_INDEX(original_file_directory, 
 'C:\\Users\\carl.harvey\\Desktop\\Prague recruitment data load\\Prague Attachments\\Prague Attachments\\Prague Attachments', 
-- 'C:\\',
-1) AS foo, original_file_directory FROM hua_file_base64);
-- C:\Users\carl.harvey\Desktop\Prague recruitment data load\Prague Attachments\Prague Attachments\Prague Attachments002b225b-493c-46e0-a0f1-301962c1003f\CV_Sacaliuc_Nicu_07-07-2017.pdf

select id, string_id from user;
select * from user;

insert into user_details(user_id, directory) SELECT left(string_id,36) AS First20, directory  /*(Or 30 if we are looking at the title)*/
FROM user;

select 

delete from user where ID = 12414;
delete from user where ID between 15493 and 18572;
select * from user_details;
delete from user_details where id between 0 and 3080;

insert into hua_file_base64(test) select user_id from user_details;

select * from user_details;

delete from user where id = 128;

set global max_allowed_packet = 32000000;