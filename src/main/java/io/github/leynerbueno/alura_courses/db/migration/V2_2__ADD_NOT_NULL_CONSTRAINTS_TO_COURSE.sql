ALTER TABLE COURSE 
    ALTER COLUMN code SET NOT NULL,
    ALTER COLUMN name SET NOT NULL,
    ALTER COLUMN instructor_id SET NOT NULL,
    ALTER COLUMN status SET NOT NULL,
    ALTER COLUMN dt_insert SET NOT NULL;