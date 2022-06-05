package io.prbaa.resource;

import io.prbaa.domain.Response;
import io.prbaa.domain.Server;
import io.prbaa.enumeration.Status;
import io.prbaa.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import static java.nio.file.Paths.get;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server")
public class ServerResource {
    private final ServerServiceImpl serverService;
    @GetMapping("/list")
    public ResponseEntity<Response> getServerDetails(){
        return ResponseEntity.ok(
                Response.builder().localDateTime(now()).
                        data(of("servers",serverService.list(30))).
                        message("Servers Retrieved").status(OK).
                        statusCode(OK.value()).build());
            }


    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server =serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder().localDateTime(now()).
                        data(of("server",server)).
                        message(server.getStatus()== Status.STATUS_UP? "Server is Up":"server is down").status(OK).
                        statusCode(OK.value()).build());
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody Server server) throws IOException {

        return ResponseEntity.ok(
                Response.builder().localDateTime(now()).
                        data(of("server",serverService.create(server))).
                        message("Server created")
                        .status(CREATED).
                        statusCode(CREATED.value()).build());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder().localDateTime(now()).
                        data(of("server",serverService.get(id))).
                        message("Server retrieved")
                        .status(OK).
                        statusCode(OK.value()).build());
    }

    @DeleteMapping("/get/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder().localDateTime(now()).
                        data(of("deleted",serverService.delete(id))).
                        message("Server deleted")
                        .status(OK).
                        statusCode(OK.value()).build());
    }

    @GetMapping(path = "/images/{fileName}",produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {

        return Files.readAllBytes(get(System.getProperty("user.home") + "/Downloads/" + fileName));
    }
}
