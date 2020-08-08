# Gitlet-

Contributors:
* Kaelan Nettleship
* Ashwin Kumar

Purpose:
A version-control system with similar functionality to git that allows for the storage and retrieval of multiple file versions, branches for multiple workflows, and merging of branches.

File Purpose:
Commit - A container class that consists of all the data necessary for each commit 
Storage - A container class necessary for the describing the current state of the git directory after each commit 
Main - A class that acts as the motor of the system and provides command-line capability via java gitlet.Main COMMAND (i.e. java gitlet.Main commit "changes")
