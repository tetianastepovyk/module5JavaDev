import dto.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public static void main(String[] args) {
        System.out.println(findMaxSalaryWorker());
        System.out.println(findMaxProjectsClient());
        System.out.println(findLongestProject());
        System.out.println(findYoungestEldestWorkers());
        System.out.println(findProjectPrices());
    }

    // метод пошуку працівника з найбільшою заробітною платою
    public static List<MaxSalaryWorkerDto> findMaxSalaryWorker() {
        List<MaxSalaryWorkerDto> maxSalaryWorkerDtoList = new ArrayList<>();

        try (Statement statement = Database.getInstance().getConnection().createStatement()) {
            String sql = Files.readString(Path.of("sql/find_max_salary_worker.sql"));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                maxSalaryWorkerDtoList.add(MaxSalaryWorkerDto.builder()
                        .name(resultSet.getString("NAME"))
                        .salary(resultSet.getInt("SALARY")).build());
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxSalaryWorkerDtoList;
    }

    // метод пошуку клієнта з найбільшою кількістю проєктів
    public static List<MaxProjectsClientDto> findMaxProjectsClient() {
        List<MaxProjectsClientDto> maxProjectsClientList = new ArrayList<>();
        try (Statement statement = Database.getInstance().getConnection().createStatement()) {
            String sql = Files.readString(Path.of("sql/find_max_projects_client.sql"));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                maxProjectsClientList.add(MaxProjectsClientDto.builder()
                        .name(resultSet.getString("NAME"))
                        .projectCount(resultSet.getInt("PROJECT_COUNT")).build());
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxProjectsClientList;
    }

    //метод пошуку проєкта з найбільшою тривалістю
    public static List<LongestProjectDto> findLongestProject() {
        List<LongestProjectDto> longestProjectList = new ArrayList<>();
        try (Statement statement = Database.getInstance().getConnection().createStatement()) {
            String sql = Files.readString(Path.of("sql/find_longest_project.sql"));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                longestProjectList.add(LongestProjectDto.builder()
                        .name(resultSet.getString("NAME"))
                        .monthCount(resultSet.getInt("MONTH_COUNT")).build());
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return longestProjectList;
    }

    //метод пошуку найстаршого та наймолодшого працівника
    public static List<YoungestEldestWorkersDto> findYoungestEldestWorkers() {
        List<YoungestEldestWorkersDto> youngestEldestWorkerList = new ArrayList<>();
        try (Statement statement = Database.getInstance().getConnection().createStatement()) {
            String sql = Files.readString(Path.of("sql/find_youngest_eldest_workers.sql"));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                youngestEldestWorkerList.add(YoungestEldestWorkersDto.builder()
                        .type(resultSet.getString("TYPE"))
                        .name(resultSet.getString("NAME"))
                        .birthday(resultSet.getString("BIRTHDAY"))
                        .build());
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return youngestEldestWorkerList;
    }
    //метод, який виводить вартість кожного проєкту
    public static List<ProjectPricesDto> findProjectPrices() {
        List<ProjectPricesDto> projectPriceList = new ArrayList<>();
        try (Statement statement = Database.getInstance().getConnection().createStatement()) {
            String sql = Files.readString(Path.of("sql/print_project_prices.sql"));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                projectPriceList.add(ProjectPricesDto.builder()
                        .name(resultSet.getString("NAME"))
                        .price(resultSet.getInt("PRICE"))
                        .build());
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return projectPriceList;
    }
}
