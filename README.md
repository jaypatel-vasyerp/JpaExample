
# JpaExample

## Api's

* ## Employee


    * ## __Add New Employee__
        > http://localhost:8080/api/employee/add
        
        Method : POST

        __body :__ 

        ```json
        {
            "employeeName":"Jay Patel",
            "bloodGroup":"O +"
        }
        ```

        __response :__
        ```json
        {
            "employeeName": "Jay Patel",
            "bloodGroup": "O +",
            "idCardDto": {
                "jobTitle": null,
                "departmentName": null,
                "confidentialInfo": ""
                }
        }
        ```

    * ## __Get All Employees__
        >http://localhost:8080/api/employee/all

        Method : GET

        Get All Employees will not give the confidential info.

        __response__ :

        ```json
        [
            {
                "employeeName": "Jay Patel",
                "bloodGroup": "O +",
                "idCardDto": {
                    "jobTitle": "Software Developer Intern",
                    "departmentName": "Development",
                }
            },
            {
                "employeeName": "Omkar Mayekar",
                "bloodGroup": "AB +",
                "idCardDto": {
                    "jobTitle": "Senior Developer",
                    "departmentName": "Development",
                }
            }
        ]
        ```
    
    * ## __Get Employee By Id__
        >http://localhost:8080/api/employee/id/1

        Method : GET

        Get Employee by id will give the confidential info

        __response__ :

        ```json
        {
            "employeeName": "Jay Patel",
            "bloodGroup": "O +",
            "idCardDto": {
                "jobTitle": "Software Developer Intern",
                "departmentName": "Development",
                "confidentialInfo": "Confidential information"
            }
        }
        ```

    * ## __Assign Id Card__
        >http://localhost:8080/api/employee/assignid

        Method : POST

        This will Asign Id Card to Employee

        __body :__

        ```json
        {
            "employeeId":1,
            "cardId":1
        }
        ```

        __response :__

        Success

    * ## __Delete Employee By Id__
        >http://localhost:8080/api/employee/delete/id/1

        Method : DELETE

        This Will Soft Delete the Employee by setting isDeleted flag true

        __response__ :
        
        Deleted

* ## IdCard

    * ## Add New Id Card
        >http://localhost:8080/api/idcard/add

        Method : POST

        __body :__

        ```json
        {
            "jobTitle":"Senior Developer",
            "departmentName":"Development",
            "confidentialInfo":"Confidential Info About Omkar"
        }
        ```

        __response :__
        ```json
        {
            "jobTitle":"Senior Developer",
            "departmentName":"Development",
            "confidentialInfo":"Confidential Info About Omkar"
        }
        ```

    * ## Get All Id Cards
        >http://localhost:8080/api/idcard/all
        
        Method : GET

        This will not give the confidential info

        __response :__

        ```json
        [
            {
                "jobTitle": "Software Developer Intern",
                "departmentName": "Development"
            },
            {
                "jobTitle": "Senior Developer",
                "departmentName": "Development",
            }
        ]
        ```

    * ## Get Id Card By Id
        >http://localhost:8080/api/idcard/id/1

        Method: GET

        This will give the confidential info

        __response :__

        ```json
        {
            "jobTitle": "Software Developer Intern",
            "departmentName": "Development",
            "confidentialInfo": "Confidential information"
        }
        ```
    * ## Delete Id Card By Id
        >http://localhost:8080/api/idcard/delete/1

        Method : DELETE

        This will soft delete the Id Card by setting the isDeleted flag true

        __response__ :
        
        Deleted



    


