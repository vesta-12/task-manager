# task manager (cli)

this is a simple command line task manager written in java

## features

- add a new task
- list all tasks
- mark task as completed
- delete 
- save to a file
- load from a file

## task structure

each task contains:

- id
- title
- description
- status (pending / completed)

## how it works

when the program starts, it loads tasks from `my_tasks.csv` if the file exists

you can then manage tasks through the console menu

when exiting the program or choosing save, tasks are written to the csv file so they can be loaded again later

## technologies

- java
- csv 