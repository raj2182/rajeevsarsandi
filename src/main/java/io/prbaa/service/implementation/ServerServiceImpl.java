package io.prbaa.service.implementation;

import io.prbaa.domain.Server;
import io.prbaa.enumeration.Status;
import io.prbaa.repo.ServerRepo;
import io.prbaa.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("creating server: {} ", server.getServerName());
        server.setImageUrl(setImageUrl());
        return serverRepo.save(server);
    }


    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("pinging server: {} ", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.STATUS_UP : Status.STATUS_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching list of server ");
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();

    }

    @Override
    public Server get(Long id) {
        log.info("fetching server by id : {} ", id);
        return serverRepo.findById(id).get();

    }

    @Override
    public Server update(Server server) {
        log.info("updating server: {} ", server.getServerName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server: {} ", id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    private String setImageUrl() {
        String[] server = {"server1.jpg", "server2.jpg", "server3.jpg", "server4.jpg"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/images/" + server[new Random().nextInt(4)]).toUriString();
    }
}
