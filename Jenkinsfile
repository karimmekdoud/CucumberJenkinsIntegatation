pipeline {
agent any
parameters {
choice(name :'TestingEnvironment', choices :[
'QA',
'DEV',
'STG',
'UAT',
'Production'
],
description : "Select the Testing Environment"
)

choice(name :'UseCloudEnv', choices :[
'False',
'True'
],
description : "Use Cloud Environment"
)
choice(name :'CloudEnvName', choices :[
'Browserstack',
'Saucelab'
],
description : "Select the Cloud Environment"
)

//string(description: 'Entre the Url : ', defaultValue: 'https://www.amazon.com/', name: 'Url')

choice(description : "Select the Operating System: " ,name :'Os', choices :[
'Mac',
'Windows',
'Linux'
]
)

choice(description : "Select the OS Version:" ,name :'Os_version', choices :[
'Big Sur',
'Catalina',
'Windows 7',
'Windows 10'
]
)
// choice(name :'Headless', choices :[
// 'False',
// 'True'
// ],
// description : "Headless Browser ?"
// )

choice(name :'BrowserName', choices :[
'Chrome',
'Safari',
'Firefox',
'IE',
'Edge',
'chrome-options',
'All'
],
description : "Select the Browser"
)

string(defaultValue: '94', description: 'Browser Version', name: 'BrowserVersion')

choice(name :'tag', choices :[
'@Amazon',
'@regression',
'@sanity',
'@smoke' ,
'@orderEvent',
'@loadTesting',
'@Ibrahim'

],
description : "Select the test suit using the corresponding Tag"
)

string(defaultValue: '4', description: 'Implicitly wait time', name: 'ImplicitlyWaitTime')

string(defaultValue: 'testkarim1980@gmail.com', description: 'email for notifications', name: 'notification_email')


}
stages {
stage("Load Properties to configuration file"){
        steps{
            script {
                def props = """

TestingEnvironment =${TestingEnvironment}
UseCloudEnv =${UseCloudEnv}
CloudEnvName =${CloudEnvName}
Os =${Os}
Os_version =${Os_version}
BrowserName =${BrowserName}
BrowserVersion =${BrowserVersion}
ImplicitlyWaitTime =${ImplicitlyWaitTime}

                """
                writeFile file: "config.properties", text: props
                def str =  readFile file: "config.properties"
                echo str
            }
         }
      }
stage('Running the test suit'){
steps{
            script {
             def os = "${Os}"
             if(os=="Windows"){
             bat(/mvn install -Dcucumber.filter.tags=${tag}/)
             }else
              {sh "mvn install -Dcucumber.filter.tags=${tag}"}
              }
               //sh "mvn install -Dcucumber.filter.tags=${tag}"
               //bat(/mvn install -Dcucumber.filter.tags=${tag}/)

}
}
}
post {
always {
echo "Test succeeded"
emailext attachmentsPattern: '**/*.html, **/*.pdf',
to: "${notification_email}",
subject: "Status and reports of pipeline: ${currentBuild.fullDisplayName}",
// body:"""<p>EXECUTED: Job <b>'${env.JOB_NAME}:${env.BUILD_NUMBER})'
//          </b></p><p>View console output at "<a href="${env.BUILD_URL}">
//          ${env.JOB_NAME}:${env.BUILD_NUMBER}</a>"</p>
//            <p><i>The Testing Environment is: ${TestingEnvironment}</i></p>
//            <p><i>The Operating System is: ${Os}</i></p>
//            <p><i>(Cucumber reports are attached.)</i></p>
//            <p><i>(Extent reports are attached.)</i></p>
//            <p><i>(Build log is attached.)</i></p>"""

           body:"""
           <p style="background-color:powderblue;">EXECUTED: Job <b> ${env.JOB_NAME}:${env.BUILD_NUMBER}
           </b></p>
           <p style="color:DodgerBlue;">View console output at "<a href="${env.BUILD_URL}">
           ${env.JOB_NAME}:${env.BUILD_NUMBER}</a>"</p>
           <p style="background-color:tomato;"><i>The Testing Environment is: ${TestingEnvironment}</i></p>
           <p style="background-color:tomato;"><i>The Operating System is: ${Os}</i></p>
           <p style="border:2px solid DodgerBlue;"><i>(Cucumber reports are attached.)</i></p>
           <p style="color:DodgerBlue;"><i>(Extent reports are attached.)</i></p>
           <p style="color:rgb(255, 99, 71);"><i>(Build log is attached.)</i></p>"""



// "
//       ${env.BUILD_URL} has result: ${currentBuild.result}
//       TestingEnvironment =${TestingEnvironment}
//       UseCloudEnv =${UseCloudEnv}
//       CloudEnvName =${CloudEnvName}
//       Os =${Os}
//       Os_version =${Os_version}
//       BrowserName =${BrowserName}
//       BrowserVersion =${BrowserVersion}
//       Url =${Url}
//       ImplicitlyWaitTime =${ImplicitlyWaitTime}
//                       "
cucumber fileIncludePattern: 'target/reports/cucumber-reports/cucumber.json', sortingMethod: 'ALPHABETICAL'
}
}
}
