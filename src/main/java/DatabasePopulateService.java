import dto.ClientDto;
import dto.ProjectDto;
import dto.ProjectWorkerDto;
import dto.WorkerDto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DatabasePopulateService {

    private static final String WORKER_FILE = "sql/worker";
    private static final String CLIENT_FILE = "sql/client";
    private static final String PROJECT_FILE = "sql/project";
    private static final String PROJECT_WORKER_FILE = "sql/project_worker";

    private static final String PREPEARE_INSERT_WORKER = "INSERT INTO worker (NAME,BIRTHDAY,LEVEL,SALARY) VALUES (?, ?, ?, ?)";
    private static final String PREPEARE_INSERT_CLIENT = "INSERT INTO client (NAME) VALUES (?)";
    private static final String PREPEARE_INSERT_PROJECT = "INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES (?, ?, ?)";
    private static final String PREPEARE_INSERT_PROJECT_WORKER = "INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES (?, ?)";

    public static void main(String[] args) {

        try (PreparedStatement preStatWorker = Database.getInstance().getConnection().prepareStatement(PREPEARE_INSERT_WORKER);
             PreparedStatement preStatClient = Database.getInstance().getConnection().prepareStatement(PREPEARE_INSERT_CLIENT);
             PreparedStatement preStatProject = Database.getInstance().getConnection().prepareStatement(PREPEARE_INSERT_PROJECT);
             PreparedStatement preStatProjectWorker = Database.getInstance().getConnection().prepareStatement(PREPEARE_INSERT_PROJECT_WORKER);
        ) {

            insertIntoWorker(preStatWorker);

            insertIntoClient(preStatClient);

            insertIntoProject(preStatProject);

            insertIntoProjectWorker(preStatProjectWorker);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertIntoWorker(PreparedStatement preStatWorker) throws SQLException {

        List<WorkerDto> workerList = readWorkersFromFile("sql/worker");

        for (WorkerDto worker : workerList) {
            preStatWorker.setString(1, worker.getName());
            preStatWorker.setString(2, worker.getBirthday());
            preStatWorker.setString(3, worker.getLevel());
            preStatWorker.setInt(4, worker.getSalary());
            preStatWorker.addBatch();
        }

        preStatWorker.executeBatch();
    }

    private static void insertIntoClient(PreparedStatement preStatClient) throws SQLException {

        List<ClientDto> clientList = readClientFromFile("sql/client");

        for (ClientDto client : clientList) {
            preStatClient.setString(1, client.getName());
            preStatClient.addBatch();
        }

        preStatClient.executeBatch();
    }

    private static void insertIntoProject(PreparedStatement preStatProject) throws SQLException {

        List<ProjectDto> projectList = readProjectFromFile("sql/project");

        for (ProjectDto project : projectList) {
            preStatProject.setInt(1, project.getClientID());
            preStatProject.setString(2, project.getStartDate());
            preStatProject.setString(3, project.getFinishDate());
            preStatProject.addBatch();
        }

        preStatProject.executeBatch();

    }

    private static void insertIntoProjectWorker(PreparedStatement preStatProject) throws SQLException {

        List<ProjectWorkerDto> projectWorkerList = readProjectWorkerFromFile("sql/project_worker");

        for (ProjectWorkerDto projectWorker : projectWorkerList) {
            preStatProject.setInt(1, projectWorker.getProjectId());
            preStatProject.setInt(2, projectWorker.getWorkerId());
            preStatProject.addBatch();
        }
        preStatProject.executeBatch();
    }



    private static List<WorkerDto> readWorkersFromFile(String location) {

        List<WorkerDto> workerList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(location))) {
            while (reader.ready()) {
                String strLine = reader.readLine();
                strLine = strLine.replaceAll("[(');]", "");
                String[] strWorkerData = strLine.trim().split(",");
                WorkerDto worker = WorkerDto.builder()
                        .name(strWorkerData[0])
                        .birthday(strWorkerData[1])
                        .level(strWorkerData[2])
                        .salary(Integer.parseInt(strWorkerData[3]))
                        .build();
                workerList.add(worker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workerList;
    }

    private static List<ClientDto> readClientFromFile(String location) {

        List<ClientDto> clientList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(location))) {
            while (reader.ready()) {
                String strLine = reader.readLine();
                strLine = strLine.replaceAll("[(');]", "");
                String[] strClientData = strLine.trim().split(",");
                ClientDto client = ClientDto.builder()
                        .name(strClientData[0])
                        .build();
                clientList.add(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    private static List<ProjectDto> readProjectFromFile(String location) {

        List<ProjectDto> projectList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(location))) {
            while (reader.ready()) {
                String strLine = reader.readLine();
                strLine = strLine.replaceAll("[(');]", "");
                String[] strProjectData = strLine.trim().split(",\\s*|,");
                ProjectDto project = ProjectDto.builder()
                        .clientID(Integer.parseInt(strProjectData[0]))
                        .startDate(strProjectData[1])
                        .finishDate(strProjectData[2])
                        .build();
                projectList.add(project);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projectList;
    }


    private static List<ProjectWorkerDto> readProjectWorkerFromFile(String location) {

        List<ProjectWorkerDto> projectWorkerList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(location))) {
            while (reader.ready()) {
                String strLine = reader.readLine();
                strLine = strLine.replaceAll("[(');]", "");
                String[] strProjectWorkerData = strLine.trim().split(",\\s*|,");
                ProjectWorkerDto project = ProjectWorkerDto.builder()
                        .projectId(Integer.parseInt(strProjectWorkerData[0]))
                        .workerId(Integer.parseInt(strProjectWorkerData[1]))
                        .build();
                projectWorkerList.add(project);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projectWorkerList;
    }

}
