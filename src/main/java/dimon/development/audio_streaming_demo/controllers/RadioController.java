package dimon.development.audio_streaming_demo.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/stream")
public class RadioController {

    @GetMapping
    public String test(){
        return "/stream";
    }

    @GetMapping(value = "/playlist.m3u8", produces = "application/vnd.apple.mpegurl")
    public ResponseEntity<Resource> getSourceOfAudio() {
        Resource resource = new FileSystemResource("src/main/resources/output.m3u8");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.apple.mpegurl")
                .body(resource);
    }

        @GetMapping(value = "/{fileName:.+}", produces = "audio/mpeg")
    public ResponseEntity<FileSystemResource> getSegment(@PathVariable String fileName) {
        FileSystemResource segmentResource = new FileSystemResource("src/main/resources/" + fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .body(segmentResource);
    }

}
